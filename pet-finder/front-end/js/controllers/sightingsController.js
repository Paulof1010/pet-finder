import { missingPetsService } from "../services/missingPetsService.js";
import { renderMissingPetsView } from "../views/missingPetsView.js";

let map = null;
let markers = [];

/* =========================
   INIT
   ========================= */
async function init() {
  const app = document.getElementById("app");
  app.innerHTML = renderMissingPetsView();

  initMap();

  try {
    const pets = await missingPetsService.getAllLostPets();
    displayPets(pets);
    updateMap(pets);
  } catch (err) {
    console.error("Failed to load missing pets:", err);
    app.innerHTML += "<p>Failed to load missing pets.</p>";
  }
}

/* =========================
   IMAGE RESOLVER
   Priority:
   1) Cloudinary (backend)
   2) Frontend local image
   3) Generic fallback
   ========================= */
function resolvePetImage(pet) {
  if (
    pet.animal &&
    pet.animal.pictureUrls &&
    Array.isArray(pet.animal.pictureUrls) &&
    pet.animal.pictureUrls.length > 0
  ) {
    return pet.animal.pictureUrls[0];
  }

  if (pet.animal && pet.animal.id) {
    return `img/sightings/${pet.animal.id}.jpg`;
  }

  return "img/logo/logo.jpg";
}

/* =========================
   DISPLAY CARDS
   ========================= */
function displayPets(pets) {
  const grid = document.getElementById("pets-grid");
  grid.innerHTML = "";

  pets.forEach((pet) => {
    if (pet.type !== "SIGHTING") return;

    const imageUrl = resolvePetImage(pet);

    const card = document.createElement("div");
    card.className = "pet-card";
    card.innerHTML = `
      <img 
        src="${imageUrl}" 
        alt="pet ${pet.animal?.id ?? ""}"
        onerror="this.src='img/sightings/${pet.animal.id}.jpg'"
      />
      <h3>Animal type: ${pet.animal.type}</h3>
      <p><strong>ID:</strong> ${pet.animal.id}</p>
      <p><strong>Name:</strong> ${pet.animal.name ?? "Unknown"}</p>
      <p><strong>Last Seen:</strong> ${new Date(
        pet.date
      ).toLocaleDateString()}</p>
      <p><strong>Species:</strong> ${pet.animal.breed}</p>
      <p><strong>Color:</strong> ${pet.animal.color}</p>
      <p><strong>Description:</strong> ${pet.comment}</p>
    `;

    grid.appendChild(card);
  });
}

/* =========================
   MAP
   ========================= */
function initMap() {
  map = L.map("pet-map").setView([38.7235, -9.1489], 10);

  L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: "&copy; OpenStreetMap contributors",
  }).addTo(map);
}

function updateMap(pets) {
  markers.forEach((m) => map.removeLayer(m));
  markers = [];

  const bounds = [];

  pets.forEach((pet) => {
    if (pet.type !== "SIGHTING") return;
    if (!pet.latitude || !pet.longitude) return;

    const marker = L.marker([pet.latitude, pet.longitude]).addTo(map);

    marker.bindPopup(`
      <strong>${pet.animal.name ?? "Unknown"} (${pet.animal.breed})</strong><br>
      Color: ${pet.animal.color}<br>
      Date: ${new Date(pet.date).toLocaleDateString()}
    `);

    markers.push(marker);
    bounds.push([pet.latitude, pet.longitude]);
  });

  if (bounds.length) {
    map.fitBounds(bounds, { padding: [50, 50] });
  }
}

export default { init };
