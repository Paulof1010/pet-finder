const routes = {
  home: {
    path: "/",
    controller: "homeController",
  },
  about: {
    path: "/about",
    controller: "aboutController",
  },
  missingPets: {
    path: "/missingPets",
    controller: "missingPetsController",
  },
  sightings: {
    path: "/sightings",
    controller: "sightingsController",
  },
  report: {
    path: "/report",
    controller: "reportController",
  },
  donations: {
    path: "/donations",
    controller: "donationsController",
  },
  login: {
    path: "/login",
    controller: "loginController",
  },
  currentPath: {
    path: "",
    controller: "",
  },
};

export default routes;
