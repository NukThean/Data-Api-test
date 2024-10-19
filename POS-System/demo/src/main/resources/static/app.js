document.addEventListener("DOMContentLoaded", function () {
  document;
  const productForm = document.querySelector(".productForm"); // Define the form element

  productForm.addEventListener("submit", function (event) {
    event.preventDefault(); // Prevent the form from submitting in the traditional way
    // Collect the product form data
    const productCode = document.querySelector(".productCode").value;
    const productName = document.querySelector(".productName").value;
    const productQuantity = document.querySelector(".productQuantity").value;
    const productCategory = document.querySelector(".productCategory").value;
    const productDescription = document.querySelector(
      ".productDescription"
    ).value;
    const costPrice = document.querySelector(".costPrice").value;
    const sellingPrice = document.querySelector(".sellingPrice").value;
    const isActive = document.querySelector(".isActive").value;
    const vendorId = document.querySelector(".vendorId").value;
    // Create a FormData object
    const formData = new FormData();
    const fileInput = document.querySelector(".fileInput");
    const file = fileInput.files[0];
    const fileExtension = file.name.split(".").pop(); // Get the file extension

    // Append the selected file to the form data
    formData.append("file", file);
    formData.append("productCode", productCode);

    // Prepare product data object
    const productData = {
      code: productCode,
      name: productName,
      quantity: productQuantity,
      category: productCategory,
      description: productDescription,
      costPrice: parseFloat(costPrice),
      salePrice: parseFloat(sellingPrice),
      image: productCode + fileExtension,
      createdAt: new Date().toISOString(),
      updatedAt: new Date().toISOString(),
      isActive: isActive === "true", // Convert to boolean
      vendor: { id: parseInt(vendorId) }, // Vendor is now an object with an id
    };

    // Check if a file is selected
    if (fileInput.files.length === 0) {
      document.querySelector(".response").innerText =
        "Please select a file to upload.";
      return;
    }

    fetch("api/pos/product", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(productData),
    })
      .then((response) => {
        if (response.ok) {
          // Optionally, reset the form after successful submission
          productForm.reset();
          handleFileUpload(formData);
        } else {
          console.error("Failed to register product", error);
        }
      })
      .catch((error) => console.error("Error registering product:", error));
  });
});

function handleFileUpload(formData) {
  fetch("api/pos/imageUpload", {
    method: "POST",
    body: formData,
  })
    .then((response) => response.text())
    .then((data) => {
      document.querySelector(".response").innerText = data; // Show response message
      fetchProducts();
    })
    .catch((error) => {
      document.querySelector(".response").innerText = "File upload failed.";
      console.error("Error:", error);
    });
}

function fetchProducts() {
  fetch("api/pos/product")
    .then((response) => response.json())
    .then((data) => {
      const productList = document.querySelector(".productList");
      productList.innerHTML = "";
      data.forEach((product) => {
        const div = document.createElement("div");
        div.innerHTML = `ID: ${product.id}, Name: ${product.name}`;
        productList.appendChild(div);
      });
    })
    .catch((error) => console.error("Error fetching products:", error));
}
// Fetch students on page load
window.onload = fetchProducts;

document
  .querySelector(".addProductButton")
  .addEventListener("click", function () {
    const productForm = document.querySelector(".productFormhidden");
    productForm.className = "productForm";
  });

document
  .querySelector(".deleteProductButton")
  .addEventListener("click", function () {
    const productForm = document.querySelector(".deleteFormhidden");
    productForm.className = "deleteForm";
  });

// Handle delete submission
document
  .querySelector(".deleteFormhidden")
  .addEventListener("submit", function (event) {
    fetch(
      "/api/pos/product/" + document.querySelector(".deleteproductId").value,
      {
        method: "DELETE",
      }
    ).then((response) => {
      if (response.ok) {
        fetchProducts(); // Refresh student list after successful delete
      } else {
        console.error("Failed to delete product");
      }
    });
    event.preventDefault(); // Prevent form from submitting normally
  });

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
        const LoginContainer = document.querySelector(".login-container");
        LoginContainer.className = "login-containerHidden";
        const productContainer = document.querySelector(
          ".product-containerHidden"
        );
        productContainer.className = "product-container";
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
