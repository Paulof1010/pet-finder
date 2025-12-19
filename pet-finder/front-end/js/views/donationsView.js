export default {
  render() {
    const container = document.getElementById("app");

    container.innerHTML = `
      <section class="donations">
        <h1 class="donations-title">Donations</h1>

        <div class="donations-card">
          <h2>Support Our Mission</h2>
          <p>
            Every day, pets go missing and families search for answers. Our mission
            is to make those reunions faster, easier, and accessible to everyone.
          </p>

          <p>
            Pet Finder is a community-driven platform that helps people report lost
            pets, search for found animals, and connect with others who want to help.
            To keep this service running smoothly and available to all, we rely on
            community support.
          </p>

          <h2>Why Donate?</h2>

          <ul class="donations-list">
            <li>💻 Maintain our servers and keep the platform online 24/7</li>
            <li>🔒 Ensure data security and reliability</li>
            <li>🚀 Improve features and performance</li>
            <li>🌍 Keep the app free and accessible for everyone</li>
          </ul>

          <p>
            Every contribution, no matter the size, directly supports the
            sustainability of the platform and helps bring more pets back home.
          </p>

          <h2>How You Can Help</h2>

          <ul class="donations-list">
            <li>Make a one-time donation</li>
            <li>Share the platform with your community</li>
            <li>Help spread awareness about lost and found pets</li>
          </ul>

          <h2 class="donations-thankyou">Thank You ❤️</h2>

          <p class="donations-highlight">
            Your support means more than just keeping servers online — it means hope
            for families, safety for pets, and stronger communities.
          </p>
        </div>
      </section>
    `;
  },
};
