const productForm = document.querySelector(".product-form");

const productList = document.querySelector(".product-list");

const addProductBtn = document.querySelector(".action-buttons .add");
const deleteProductBtn = document.querySelectorAll(".delete");

function fetchProducts() {
  fetch("api/pos/product")
    .then((response) => response.json())
    .then((data) => {
      productList.innerHTML = "";
      data.forEach((product) => {
        const div = document.createElement("div");
        div.innerHTML = `<div class="product-card">
          <div class="product-content">
            <span class="product-code">${product.code}</span>
            <h3>${product.name}</h3>
            <p>${product.description}</p>
          </div>

          <button class="btn delete">Delete</button>
        </div>`;
        productList.appendChild(div);
      });
    })
    .catch((error) => console.error("Error fetching products:", error));
}
// Fetch students on page load
window.onload = fetchProducts;

addProductBtn.addEventListener("click", function () {
  productForm.classList.toggle("hidden");
});

deleteProductBtn.forEach((btn) => {
  btn.addEventListener("click", function (e) {
    console.log(e.target);
  });
});
