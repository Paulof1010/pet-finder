/**
 * Reusable pet card rendering function
 * @param {Object} pet - Pet or sighting object
 * @param {Function} onClick - callback for when the card is clicked
 * @param {Boolean} isSighting - optional, true if object is a sighting
 * @returns DOM element
 */
export function renderPetCard(pet, onClick, isSighting = false) {
  const card = document.createElement("div");
  card.classList.add("pet-card");

  // Highlight old reports (>30 days)
  const dateReported = new Date(pet.dateReported);
  const now = new Date();
  if ((now - dateReported) / (1000 * 60 * 60 * 24) > 30) {
    card.classList.add("old-report");
  }

  // Determine displayed info
  const animal = isSighting ? pet.animal || {} : pet;
  const petName = animal.petName || "Unknown";
  const species = animal.species || "Unknown";
  const color = animal.color || "Unknown";
  const city = pet.city || "Unknown";
  const dateStr = dateReported.toLocaleDateString();

  card.innerHTML = `
    <img src="${
      animal.photoUrl || "/img/mock/default-pet.jpg"
    }" alt="${petName}" />
    <h3>${petName}</h3>
    <p>Species: ${species}</p>
    <p>Color: ${color}</p>
    <p>City: ${city}</p>
    <p>Reported: ${dateStr}</p>
    ${isSighting ? `<p>Type: ${pet.type || "Unknown"}</p>` : ""}
  `;

  card.addEventListener("click", () => onClick(pet));

  return card;
}
