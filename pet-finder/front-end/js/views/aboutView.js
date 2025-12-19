export default {
  render() {
    const container = document.getElementById("app");

    container.innerHTML = `
      <section class="about">
        <h1 class="about-title">About Us</h1>

        <div class="about-card">
          <p>
            We are a team of four developers driven by a shared purpose: using technology
            to solve real-world problems and create meaningful impact. We believe that
            great software goes beyond functionality — it should be built with empathy,
            responsibility, and a clear understanding of the people it serves.
          </p>

          <p>
            This project was born from the desire to create a simple, effective, and
            accessible solution to help reunite lost pets with their families. Losing
            an animal is an emotionally challenging experience, and time often plays
            a critical role. Our goal is to reduce that uncertainty by providing a
            platform that enables quick reporting, easy searching, and direct
            communication within the community.
          </p>

          <p>
            The Pet Finder application allows users to report missing animals, browse
            and filter found pets, and connect with others who want to help, all within
            a seamless and responsive Single Page Application (SPA). Every feature has
            been carefully designed to prioritize clarity, speed, and ease of use,
            even in stressful situations.
          </p>

          <p>
            Beyond the technical aspects, this project represents our belief in
            community-driven solutions. By empowering users to actively participate,
            share information, and support one another, Pet Finder becomes more than
            just an app — it becomes a network of people working together toward a
            common goal.
          </p>

          <p class="about-highlight">
            At its core, this project reflects our commitment to thoughtful,
            user-centered development, where empathy, usability, and technology come
            together to create positive and lasting outcomes — for pets, for families,
            and for the communities they belong to.
          </p>
        </div>
      </section>
    `;
  },
};
