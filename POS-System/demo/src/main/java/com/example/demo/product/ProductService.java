package com.example.demo.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.product_category.Category;
import com.example.demo.product_category.CategoryRepository;
import com.example.demo.sale_price.SalePrice;
import com.example.demo.sale_price.SalePriceRepository;
import com.example.demo.sale_price.SalePriceRequest;
import com.example.demo.vendor.Vendor;
import com.example.demo.vendor.VendorRepository;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final VendorRepository vendorRepository;
  private final SalePriceRepository salePriceRepository;
  private final CategoryRepository categoryRepository;

  // Inject both ProductRepository and VendorRepository in a single constructor
  @Autowired
  public ProductService(ProductRepository productRepository, VendorRepository vendorRepository,
      SalePriceRepository salePriceRepository, CategoryRepository categoryRepository) {
    this.productRepository = productRepository;
    this.vendorRepository = vendorRepository;
    this.salePriceRepository = salePriceRepository;
    this.categoryRepository = categoryRepository;
  }

  public List<Product> getProducts() {
    return productRepository.findAll();
  }

  public void addProduct(ProductRequest productRequest) {

    // Find vendor by ID or name
    Vendor vendor = vendorRepository.findById(productRequest.getVendorId())
        .orElseThrow(() -> new RuntimeException("Vendor not found"));

    // Find category by ID or name
    Category category = categoryRepository.findById(productRequest.getCategoryId())
        .orElseThrow(() -> new RuntimeException("Category not found"));

    // Get current date for createdAt and updatedAt fields
    Date currentDate = new Date();

    // Create new product
    Product product = new Product(productRequest.getProductCode(), productRequest.getProductName(),
        productRequest.getQuantity(), category, productRequest.getDescription(), null,
        productRequest.getImageUrl(), currentDate, currentDate, productRequest.getIsActive(),
        vendor, null);

    // Save the product
    productRepository.save(product);
    // Save the sale prices if provided
    if (productRequest.getSalePrices() != null) {
      for (SalePriceRequest salePriceReq : productRequest.getSalePrices()) {
        SalePrice salePrice =
            new SalePrice(product, salePriceReq.getMinQuantity(), salePriceReq.getMaxQuantity(),
                salePriceReq.getPricePerUnit(), salePriceReq.getEffectiveDate(),
                salePriceReq.getExpirationDate(), currentDate, currentDate);
        salePriceRepository.save(salePrice);
      }
    }
  }

  public List<Product> getActiveProducts() {
    return productRepository.findAllActive();
  }

  public Product getActiveProduct(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new RuntimeException("Product not found"));
  }

  public void softDeleteProduct(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new RuntimeException("Product not found"));
    product.setdeletedAt(LocalDateTime.now());
    productRepository.save(product);
  }


  // public void deleteProduct(Long productId) {
  // boolean exists = productRepository.existsById(productId);
  // if (!exists) {
  // throw new IllegalStateException("Product with id " + productId + " does not exists");
  // }
  // productRepository.deleteById(productId);
  // }
}


