import { renderReportView } from "../views/reportView.js";
import { reportService } from "../services/reportService.js";
import { initMap } from "../utils/mapUtils.js";

/* =========================
   CLOUDINARY CONFIG
   ========================= */
const CLOUD_NAME = "dezygulo9";
const UPLOAD_PRESET = "petfinder_unsigned";

/* =========================
   STATE
   ========================= */
let selectedLocation = null;
let uploadedPhotoUrl = null;
let isUploadingPhoto = false;

/* =========================
   INIT
   ========================= */
function init() {
  document.getElementById("app").innerHTML = renderReportView();

  initMap("map", [], [38.7167, -9.1333], 13, (lat, lng) => {
    selectedLocation = { lat, lng };
  });

  attachEventListeners();
}

/* =========================
   EVENTS
   ========================= */
function attachEventListeners() {
  const form = document.getElementById("report-form");
  const occurrenceSelect = document.getElementById("occurrence-type");
  const ownerSection = document.getElementById("owner-section");

  const photoInput = document.getElementById("photo-file");
  const previewImg = document.getElementById("photo-preview-img");

  const ownerInputs = {
    firstName: document.getElementById("user-firstName"),
    lastName: document.getElementById("user-lastName"),
    email: document.getElementById("user-email"),
    phone: document.getElementById("user-phone"),
  };

  /* =========================
     TOGGLE OWNER FIELDS
     ========================= */
  function toggleOwnerFields() {
    const isMissing = occurrenceSelect.value === "MISSING";
    ownerSection.classList.toggle("hidden", !isMissing);

    Object.values(ownerInputs).forEach((input) => {
      input.required = isMissing;
    });
  }

  occurrenceSelect.addEventListener("change", toggleOwnerFields);
  toggleOwnerFields();

  /* =========================
     PHOTO UPLOAD (CLOUDINARY)
     ========================= */
  photoInput.addEventListener("change", async () => {
    const file = photoInput.files[0];
    if (!file) return;

    isUploadingPhoto = true;
    uploadedPhotoUrl = null;

    // Preview
    const reader = new FileReader();
    reader.onload = (e) => {
      previewImg.src = e.target.result;
      previewImg.style.display = "block";
      previewImg.style.opacity = "0.5";
    };
    reader.readAsDataURL(file);

    // Upload
    const formData = new FormData();
    formData.append("file", file);
    formData.append("upload_preset", UPLOAD_PRESET);

    try {
      const res = await fetch(
        `https://api.cloudinary.com/v1_1/${CLOUD_NAME}/image/upload`,
        { method: "POST", body: formData }
      );

      const data = await res.json();

      if (!res.ok || !data.secure_url) {
        throw new Error(data?.error?.message || "Upload failed");
      }

      uploadedPhotoUrl = data.secure_url;
      previewImg.style.opacity = "1";
    } catch (err) {
      console.error("Cloudinary upload failed:", err);
      alert("Image upload failed");
      uploadedPhotoUrl = null;
      previewImg.style.display = "none";
    } finally {
      isUploadingPhoto = false;
    }
  });

  /* =========================
     SUBMIT
     ========================= */
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    if (isUploadingPhoto) {
      alert("Please wait for the image to finish uploading.");
      return;
    }

    if (!selectedLocation) {
      alert("Please select a location on the map.");
      return;
    }

    try {
      const isMissing = occurrenceSelect.value === "MISSING";

      const report = {
        animal: {
          id: null,
          type: document.getElementById("animal-type").value,
          breed: document.getElementById("pet-breed").value.trim(),
          color: document.getElementById("pet-color").value.trim(),
          owner: null,
          pictureUrls: uploadedPhotoUrl ? [uploadedPhotoUrl] : [],
        },
        comment: document.getElementById("comment").value.trim(),
        latitude: selectedLocation.lat,
        longitude: selectedLocation.lng,
        type: occurrenceSelect.value,
        date: new Date().toISOString().split("T")[0],
        owner: isMissing
          ? {
              firstName: ownerInputs.firstName.value.trim(),
              lastName: ownerInputs.lastName.value.trim(),
              email: ownerInputs.email.value.trim(),
              primaryPhone: ownerInputs.phone.value.trim(),
              animals: [],
            }
          : null,
      };

      console.log("REPORT TO SEND:", report);

      await reportService.submitReport(report);

      alert("Report submitted successfully!");
      form.reset();
      previewImg.style.display = "none";
      uploadedPhotoUrl = null;
      selectedLocation = null;
      toggleOwnerFields();
    } catch (err) {
      console.error(err);
      alert("Failed to submit report");
    }
  });
}

export default { init };
