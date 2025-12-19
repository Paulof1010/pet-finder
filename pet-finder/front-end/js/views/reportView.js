export function renderReportView() {
  return `
    <section class="report">
      <h2>Report a Lost or Found Pet</h2>

      <form class="report-form" id="report-form">

        <!-- OWNER INFO -->
        <div class="user-info hidden" id="owner-section">
          <h3>Owner Information</h3>

          <label>
            First Name
            <input type="text" id="user-firstName" />
          </label>

          <label>
            Last Name
            <input type="text" id="user-lastName" />
          </label>

          <label>
            Email
            <input type="email" id="user-email" />
          </label>

          <label>
            Phone
            <input type="tel" id="user-phone" />
          </label>

          <p class="hint">Required only when reporting a missing pet</p>
        </div>

        <!-- PET INFO -->
        <div class="pet-info">
          <label>
            Animal Type
            <select id="animal-type" required>
              <option value="dog">Dog</option>
              <option value="cat">Cat</option>
            </select>
          </label>

          <label>
            Breed
            <input type="text" id="pet-breed" required />
          </label>

          <label>
            Color
            <input type="text" id="pet-color" required />
          </label>

          <label>
            Occurrence Type
            <select id="occurrence-type" required>
              <option value="MISSING">Missing</option>
              <option value="SIGHTING">Sighting</option>
            </select>
          </label>

          <label>
            Comment
            <textarea id="comment"></textarea>
          </label>

          <!-- PHOTO UPLOAD -->
          <label>
            Pet Photo (optional)
            <input type="file" id="photo-file" accept="image/*" />
          </label>

          <div class="photo-preview">
            <img id="photo-preview-img" style="display:none; max-width:100%;" />
          </div>

          <label>
            Location (click on map)
            <div id="map"></div>
          </label>
        </div>

        <button type="submit" class="report-form-btn">
          Submit Report
        </button>
      </form>
    </section>
  `;
}
