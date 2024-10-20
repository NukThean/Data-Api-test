document.addEventListener("DOMContentLoaded", function () {
  document;
  const productForm = document.querySelector(".productForm"); // Define the form element
  const moreButton = document.querySelector(".moreButton");
  const registerButton = document.querySelector(".registerButton");

  populateCategorys();

  // Add event listener for 'click' event
  moreButton.addEventListener("click", function (event) {
    event.preventDefault(); // Prevent form submission on button click

    // Find the parent container where we will append the new divs
    const inputGroup = document.querySelector(".input-group");

    // Count how many sell price sections already exist
    const sellPriceDivs = document.querySelectorAll(".sellprice"); // Select all existing sell price divs
    const nextIndex = sellPriceDivs.length + 1;

    // Create a new div element for the new sell price section
    const newSellPriceDiv = document.createElement("div");
    newSellPriceDiv.className = `sellprice`;
    newSellPriceDiv.id = `sellprice${nextIndex}`;

    // Generate the inner HTML for the new section
    newSellPriceDiv.innerHTML = `
    <div class="selll">
      <label for="sellingPrice">Sell Price ${nextIndex}:</label>
      <input type="text" class="sellingPrice" id="sellingPrice${nextIndex}" required />
    </div>

    <div class="sell">
      <label for="minQuantity">Minimum Quantity:</label>
      <input type="text" class="minQuantity" id="minQuantity${nextIndex}" required />
    </div>

    <div class="sell">
      <label for="maxQuantity">Maximum Quantity:</label>
      <input type="text" class="maxQuantity" id="maxQuantity${nextIndex}" required />
    </div>

    <div class="sell">
      <label for="effectiveDate">Effective Date:</label>
      <input type="date" class="effectiveDate" id="effectiveDate${nextIndex}" required />
    </div>

    <div class="sell">
      <label for="expiryDate">Expiry Date:</label>
      <input type="date" class="expiryDate" id="expiryDate${nextIndex}" required />
    </div>
  `;

    // Append the new div to the input group container
    inputGroup.appendChild(newSellPriceDiv);
  });

  registerButton.addEventListener("click", async function (event) {
    event.preventDefault(); // Prevent the form from submitting in the traditional way
    // Collect the product form data
    console.log("Form submitted!");
    const productCode = document.querySelector(".productCode").value;
    const productName = document.querySelector(".productName").value;
    const productQuantity = document.querySelector(".productQuantity").value;
    const productCategory = document.querySelector(".productCategory").value;
    const productDescription = document.querySelector(
      ".productDescription"
    ).value;
    const sellPricesData = [];
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
    var sellPriceCount = document.querySelectorAll(".sellprice").length;

    for (let i = 1; i <= sellPriceCount; i++) {
      const sellPrice = document.getElementById(`sellingPrice${i}`).value;
      const minQuantity = document.getElementById(`minQuantity${i}`).value;
      const maxQuantity = document.getElementById(`maxQuantity${i}`).value;
      const effectiveDate = document.getElementById(`effectiveDate${i}`).value;
      const expiryDate = document.getElementById(`expiryDate${i}`).value;

      sellPricesData.push({
        sellPrice,
        minQuantity,
        maxQuantity,
        effectiveDate,
        expiryDate,
      });
      console.log("what the hell");
    }

    // Prepare product data object
    const productData = {
      code: productCode,
      name: productName,
      quantity: productQuantity,
      category: productCategory,
      description: productDescription,
      salePrice: sellPricesData,
      image: productCode + fileExtension,
      createdAt: new Date().toISOString(),
      updatedAt: new Date().toISOString(),
      isActive: isActive === "true", // Convert to boolean
      vendor: vendorId, // Vendor is now an object with an id
    };

    console.log("Product Data:", productData);
    console.log("Sale price:", sellPricesData);

    // Check if a file is selected
    if (fileInput.files.length === 0) {
      document.querySelector(".response").innerText =
        "Please select a file to upload.";
      return;
    }

    const csrfData = await fetchCsrfTokenAddProduct();
    if (!csrfData) {
      console.error("Unable to fetch CSRF token. Cannot proceed.");
      return;
    }

    fetch("api/pos/product/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "X-CSRF-TOKEN": csrfData, // Set CSRF token in the request headers
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

function populateCategorys() {
  fetch("api/pos/product/category")
    .then((response) => response.json())
    .then((data) => {
      const categorySelect = document.querySelector(".productCategory");
      data.forEach((category) => {
        const option = document.createElement("option");
        option.value = category.id;
        option.text = category.name;
        console.log("Category ID:", option.value);
        console.log("Category Name:", option.text);
        categorySelect.appendChild(option);
      });
    })
    .catch((error) => console.error("Error:", error));
}

async function fetchCsrfTokenAddProduct() {
  const response = await fetch("http://localhost:8080/api/pos/product/add", {
    method: "GET",
    credentials: "same-origin", // ensures cookies (like JSESSIONID) are included
  });
  const csrfToken = response.headers.get("X-CSRF-TOKEN"); // Get token from headers
  console.log("CSRF Token:", csrfToken);
  return csrfToken;
}
