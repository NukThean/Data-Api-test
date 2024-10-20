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
