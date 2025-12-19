import routes from "./routes.js";

function setCurrentRoute({ path, controller }) {
  routes.currentPath.path = path;
  routes.currentPath.controller = controller;
}

function navigate(path, firstload = false) {
  if (path === routes.currentPath.path) return;

  const routeKey = Object.keys(routes).find((k) => routes[k].path === path);
  const route = routes[routeKey] || routes.home;

  setCurrentRoute(route);
  setActiveNavLink(route.path);

  if (firstload) history.replaceState(route, "", route.path);
  else history.pushState(route, "", route.path);

  launchController(route.controller);
  setAnchorEventListener();
}

function handlePopState({ state }) {
  const route = state || routes.home;
  setCurrentRoute(route);
  setActiveNavLink(route.path);
  launchController(route.controller);
  setAnchorEventListener();
}

async function launchController(controllerName) {
  try {
    const { default: controller } = await import(
      `./controllers/${controllerName}.js`
    );
    controller.init();
  } catch (err) {
    console.error("Controller failed to load:", err);
  }
}

function setAnchorEventListener() {
  document.querySelectorAll("a[href^='/']").forEach((a) => {
    a.onclick = (e) => {
      e.preventDefault();
      navigate(a.getAttribute("href"));
    };
  });
}

function setActiveNavLink(path) {
  document.querySelectorAll(".main-nav a").forEach((link) => {
    link.classList.toggle("active", link.getAttribute("href") === path);
  });
}

function init() {
  navigate(window.location.pathname, true);
  addEventListener("popstate", handlePopState);
  setAnchorEventListener();
}

export default { init, navigate };
