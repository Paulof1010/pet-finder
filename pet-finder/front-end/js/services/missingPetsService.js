// js/services/missingPetsService.js

// js/services/missingPetsService.js
const API_BASE = "http://13.40.192.86:8080";
// adjust if your backend is on a different path/port

export const missingPetsService = {
  getAllLostPets,
  getAnimalById,
};

/**
 * Fetch all lost animals from the backend.
 * Accepts optional filters: species, color, city.
 * Returns a Promise that resolves to an array of animals.
 */
async function getAllLostPets(filters = {}) {
  let url = `${API_BASE}/occurrence`;

  // Build query params if filters exist
  const params = new URLSearchParams();
  if (filters.breed) params.append("breed", filters.breed);
  if (filters.color) params.append("color", filters.color);
  if (filters.city) params.append("city", filters.city);

  if ([...params].length) url += `?${params.toString()}`;

  try {
    const response = await fetch(url);
    if (!response.ok) throw new Error("Failed to fetch lost animals");
    return await response.json();
  } catch (error) {
    console.error("MissingPetsService.getAllLostPets:", error);
    return [];
  }
}

/**
 * Fetch detailed info about a single animal by ID,
 * including its occurrences (last seen locations, dates, etc.).
 */
async function getAnimalById(animalId) {
  try {
    const response = await fetch(`${API_BASE}/pets/${animalId}`);
    if (!response.ok)
      throw new Error(`Failed to fetch animal with id ${animalId}`);
    return await response.json();
  } catch (error) {
    console.error("MissingPetsService.getAnimalById:", error);
    return null;
  }
}
