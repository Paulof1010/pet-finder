export default {
  render() {
    const app = document.getElementById("app");

    app.innerHTML = `
      <section class="auth-page">
        <form class="report-form" id="auth-form">

          <!-- LOGIN COLUMN -->
          <div class="user-info">
            <h2>Login</h2>
            <label>
              Email:
              <input type="email" id="login-email" required />
            </label>

            <label>
              Password:
              <input type="password" id="login-password" required />
            </label>

            <button type="button" class="report-form-btn" id="login-btn">
              Login
            </button>
          </div>

          <!-- REGISTER COLUMN -->
          <div class="pet-info">
            <h2>Register</h2>
            <label>
              First Name
              <input type="text" id="reg-firstName" required />
            </label>

            <label>
              Last Name
              <input type="text" id="reg-lastName" required />
            </label>

            <label>
              Email:
              <input type="email" id="reg-email" required />
            </label>

            <label>
              Password:
              <input type="password" id="reg-password" required />
            </label>

            <label>
              Confirm Password:
              <input type="password" id="reg-confirm-password" required />
            </label>

            <label>
              Phone Number:
              <input type="tel" id="reg-phone" />
            </label>

            <label>
              Secondary Phone Number:
              <input type="tel" id="reg-secondary-phone" />
            </label>

            <button type="button" class="report-form-btn" id="register-btn">
              Register
            </button>
          </div>

        </form>

        <p id="auth-message"></p>
      </section>
    `;

    this.addEvents();
  },

  addEvents() {
    document.getElementById("login-btn").addEventListener("click", () => {
      const email = document.getElementById("login-email").value;
      const password = document.getElementById("login-password").value;

      if (!email || !password) {
        this.showMessage("Please fill in login fields", true);
        return;
      }

      this.showMessage("Logging in...", false);
      console.log("LOGIN:", { email, password });
    });

    document.getElementById("register-btn").addEventListener("click", () => {
      const password = document.getElementById("reg-password").value;
      const confirm = document.getElementById("reg-confirm-password").value;

      if (password !== confirm) {
        this.showMessage("Passwords do not match", true);
        return;
      }

      this.showMessage("Registering...", false);
      console.log("REGISTER");
    });
  },

  showMessage(text, isError) {
    const msg = document.getElementById("auth-message");
    msg.textContent = text;
    msg.style.color = isError ? "red" : "green";
  },
};
