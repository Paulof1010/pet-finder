import homeView from "../views/homeView.js";

function init() {
  document.getElementById("app").innerHTML = homeView();
}

export default { init };
