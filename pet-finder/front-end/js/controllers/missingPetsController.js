import { missingPetsService } from "../services/missingPetsService.js";
import { renderMissingPetsView } from "../views/missingPetsView.js";

let map = null;
let markers = [];

async function init() {
  const app = document.getElementById("app");
  app.innerHTML = renderMissingPetsView();

  initMap(); // Initialize the map

  try {
    const pets = await missingPetsService.getAllLostPets();
    displayPets(pets);
    updateMap(pets); // Add markers to the map
  } catch (err) {
    console.error("Failed to load missing pets:", err);
    app.innerHTML += "<p>Failed to load missing pets.</p>";
  }
}

function displayPets(pets) {
  const grid = document.getElementById("pets-grid");
  grid.innerHTML = "";

  pets.forEach((pet) => {
    if (pet.type === "MISSING") {
      const card = document.createElement("div");
      card.className = "pet-card";
      card.innerHTML = `
      <img src="img/missingPets/${pet.animal.id}.jpg" alt="${
        pet.animal.name
      }" />
      <h3>Animal type: ${pet.animal.type}</h3>
      <p><strong><i>ID:</i></strong> ${pet.animal.id}</p>
      <p><strong><i>Name:</i></strong> ${pet.animal.name}</p>
      <p><strong><i>Last Seen:</i></strong> ${new Date(
        pet.date
      ).toLocaleDateString()}</p>
      <p><strong><i>Species:</i></strong> ${pet.animal.breed}</p>
      <p><strong><i>Color:</i></strong> ${pet.animal.color}</p>
      <p><strong><i>Description:</i></strong> ${pet.comment}</p>
      <p><strong><i>Owner:</i></strong> ${pet.owner.firstName} ${
        pet.owner.lastName
      }</p>
      <p><strong><i>Contacts:</i></strong><ul style="list-style-type:none; padding:0; margin:0;">
        <li>Email: ${pet.owner.email}</li>
        <li>Phone: ${pet.owner.primaryPhone}</li>
      </ul></p>
    `;
      grid.appendChild(card);
    }
  });
}

/* ---------------- MAP ---------------- */
function initMap() {
  map = L.map("pet-map").setView([38.7235, -9.1489], 10);

  L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: "&copy; OpenStreetMap contributors",
  }).addTo(map);
}

function updateMap(pets) {
  // Remove existing markers
  markers.forEach((m) => map.removeLayer(m));
  markers = [];

  const bounds = [];

  pets.forEach((pet) => {
    if (pet.type === "MISSING") {
      if (!pet.latitude || !pet.longitude) return;

      const marker = L.marker([pet.latitude, pet.longitude]).addTo(map);
      marker.bindPopup(`
      <strong>${pet.name} (${pet.breed})</strong><br>
      Color: ${pet.color}<br>
      City: ${pet.city}
    `);

      markers.push(marker);
      bounds.push([pet.latitude, pet.longitude]);
    }
  });

  if (bounds.length) {
    map.fitBounds(bounds, { padding: [50, 50] });
  }
}

export default { init };
