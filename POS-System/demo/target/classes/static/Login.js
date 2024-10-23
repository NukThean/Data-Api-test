const loginWrapper = document.querySelector(".login-wrapper");
const productContainer = document.querySelector(".product-container");

//-------------------------------------------// Function to fetch the CSRF token from the server via a GET request
async function fetchCsrfToken() {
  const response = await fetch("http://localhost:8080/api/pos/user/login", {
    method: "GET",
    credentials: "same-origin", // ensures cookies (like JSESSIONID) are included
  });
  const csrfToken = response.headers.get("X-CSRF-TOKEN"); // Get token from headers
  console.log("CSRF Token:", csrfToken);
  return csrfToken;
}

// Event listener for form submission
document
  .getElementById("loginForm")
  .addEventListener("submit", async function (e) {
    e.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    // Retrieve the CSRF token via a GET request
    const csrfData = await fetchCsrfToken();
    if (!csrfData) {
      console.error("Unable to fetch CSRF token. Cannot proceed.");
      return;
    }

    console.log("CSRF Token login:", csrfData);
    console.log("Username:", username);
    console.log("Password:", password);

    // Proceed with the login request using the CSRF token
    try {
      const response = await fetch("http://localhost:8080/api/pos/user/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "X-CSRF-TOKEN": csrfData, // Set CSRF token in the request headers
        },
        body: JSON.stringify({
          username: username,
          password: password,
        }),
      });

      if (response.ok) {
        console.log("Login successful");
        loginWrapper.classList.add("hidden");
        productContainer.classList.remove("hidden");
        // Redirect to dashboard or other actions after successful login
        //window.location.href = "/dashboard"; // Example redirect
      } else {
        const errorMessage = document.getElementById("error-message");
        errorMessage.textContent = "Invalid login credentials.";
      }
    } catch (error) {
      console.error("Error during login request:", error);
    }
  });
