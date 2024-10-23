import { useEffect, useState } from "react";
import ProductContainer from "./components/ProductContainer";
import axios from "axios";

function App() {
  const [datas, setDatas] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [csrfToken, setCsrfToken] = useState("");

  useEffect(() => {
    async function fetchProducts() {
      setIsLoading(true);
      try {
        const res = await axios.get("http://localhost:8080/api/pos/product", {
          withCredentials: true, // Ensure cookies (e.g., JSESSIONID) are included
        });

        const data = res.data;
        setDatas(data);
        console.log(data);

        // Fetch CSRF token from headers
        const token = res.headers["x-csrf-token"];
        console.log("CSRF Token:", token);
        setCsrfToken(token); // Store CSRF token in state
      } catch (error) {
        console.error("Error fetching products:", error);
      } finally {
        setIsLoading(false);
      }
    }
    fetchProducts();
  }, []);

  async function softDeleteProduct(id) {
    console.log("CSRF Token delete:", csrfToken);
    // Ensure CSRF token is included in the POST request
    const res = await fetch(
      `http://localhost:8080/api/pos/product/soft-delete/${id}`,
      {
        method: "PUT",
        credentials: "include", // ensures cookies (like JSESSIONID) are included
        headers: {
          "Content-Type": "application/json", // Content-Type for POST request
          "X-CSRF-TOKEN": csrfToken, // Pass the CSRF token from state
        },
        body: JSON.stringify({ id }), // Send product ID in the body
      }
    );

    if (res.ok) {
      const data = await res.json();
      console.log("Delete token:" + csrfToken);
      console.log("Response:", data);
    } else {
      console.error("Failed to delete product:", res.status);
    }
  }

  async function handleDelete(id) {
    setDatas(datas.filter((d) => d.id !== id)); // Optimistically update UI
    console.log(id);
    await softDeleteProduct(id); // Soft delete on the backend
  }

  return (
    <div>
      <ProductContainer
        datas={datas}
        handleDelete={handleDelete}
        isLoading={isLoading}
      />
    </div>
  );
}

export default App;
