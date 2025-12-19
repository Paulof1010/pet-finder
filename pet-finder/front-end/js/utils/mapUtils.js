let mapInstance = null;
let markers = [];

export function initMap(
  containerId = "map",
  pets = [],
  center = [38.7167, -9.1333],
  zoom = 13,
  onClick = null
) {
  if (!mapInstance) {
    mapInstance = L.map(containerId).setView(center, zoom);
    L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
      attribution: "© OpenStreetMap",
    }).addTo(mapInstance);
  } else {
    mapInstance.setView(center, zoom);
  }

  markers.forEach((m) => m.remove());
  markers = [];

  pets.forEach((pet) => {
    const marker = L.marker([pet.lat, pet.lng])
      .addTo(mapInstance)
      .bindPopup(
        `<b>${pet.petName || "Unknown"}</b><br>${pet.animal}<br>${
          pet.description
        }`
      );
    markers.push(marker);
  });

  if (onClick) {
    mapInstance.off("click");
    mapInstance.on("click", (e) => {
      const { lat, lng } = e.latlng;
      if (markers.temp) markers.temp.remove();
      markers.temp = L.marker([lat, lng]).addTo(mapInstance);
      onClick(lat, lng);
    });
  }
}
