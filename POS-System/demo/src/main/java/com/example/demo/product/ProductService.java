package com.example.demo.product;


import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.sale_price.SalePrice;
import com.example.demo.vendor.Vendor;
import com.example.demo.vendor.VendorRepository;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final VendorRepository vendorRepository;

  // Inject both ProductRepository and VendorRepository in a single constructor
  @Autowired
  public ProductService(ProductRepository productRepository, VendorRepository vendorRepository) {
    this.productRepository = productRepository;
    this.vendorRepository = vendorRepository;
  }

  public List<Product> getProducts() {
    return productRepository.findAll();
  }

  public void addProduct(Product product) {

    // Save the product
    productRepository.save(product);
  }

  // public void addProduct(Product product) {
  // // Ensure SalePrice is linked to Product before saving
  // if (product.salePrices() != null) {
  // for (SalePrice price : product.salePrices()) {
  // price.setProduct(product); // Ensure the relationship is set correctly
  // }
  // }

  // productRepository.save(product);
  // }


  public void deleteProduct(Long productId) {
    boolean exists = productRepository.existsById(productId);
    if (!exists) {
      throw new IllegalStateException("Product with id " + productId + " does not exists");
    }
    productRepository.deleteById(productId);
  }
}


