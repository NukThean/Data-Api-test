package com.example.demo.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/pos/product")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public List<Product> getProducts() {
    return productService.getProducts();
  }

  // @GetMapping
  // public ResponseEntity<List<Product>> getProducts() {
  // List<Product> products = productService.getProducts();
  // return new ResponseEntity<>(products, HttpStatus.OK);
  // }

  @PostMapping("add")
  public void addProduct(@RequestBody ProductRequest productRequest) {
    System.out.println("productRequest = " + productRequest);
    System.out.println("Product code: " + productRequest.getProductCode());
    productService.addProduct(productRequest);
  }

  @GetMapping("add")
  public ResponseEntity<String> getProduct() {
    return ResponseEntity.ok("getProduct endpoint is working.");
  }

  @DeleteMapping(path = "{productId}")
  public void deleteProduct(@PathVariable("productId") long productId) {
    productService.deleteProduct(productId);
  }


}
