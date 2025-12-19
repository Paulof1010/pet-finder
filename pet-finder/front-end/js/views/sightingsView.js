/**
 * Missing Pets View
 * - Returns HTML for filters, pets grid, and map popup
 * - DOM elements are given IDs for controller access
 */

export function renderMissingPetsView() {
  return `
    <div class="filters">
      <label>
        Species
        <select id="filter-species">
          <option value="">All</option>
          <option value="Dog">Dog</option>
          <option value="Cat">Cat</option>
        </select>
      </label>
      <label>
        Color
        <input type="text" id="filter-color" placeholder="Enter color" />
      </label>
      <label>
        City
        <input type="text" id="filter-city" placeholder="Enter city" />
      </label>
      <button id="apply-filters">Apply Filters</button>
    </div>

    <div class="pets-grid" id="pets-grid"></div>
    <div id="pet-map" style="height: 400px; width: 100%; margin-top: 40px;"></div>

    <div class="pet-map-popup" id="pet-map-popup">
      <div id="pet-map" style="height: 80%;"></div>
    </div>
  `;
}
