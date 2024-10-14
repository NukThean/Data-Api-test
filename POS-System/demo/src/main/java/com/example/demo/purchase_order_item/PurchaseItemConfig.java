package com.example.demo.purchase_order_item;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.product.Product;
import com.example.demo.product.ProductRepository;
import com.example.demo.purchase_order.PurchaseOrder;
import com.example.demo.purchase_order.PurchaseOrderRepository;


@Configuration
public class PurchaseItemConfig {
  @Bean
  CommandLineRunner commandLineRunnerPurchaseItemConfig(
      PurchaseItemRepository purchaseItemRepository, ProductRepository productRepository,
      PurchaseOrderRepository purchaseOrderRepository) {

    return args -> {
      long count = purchaseItemRepository.count();
      if (count == 0) {

        // PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(1L)
        // .orElseThrow(() -> new IllegalArgumentException("Purchase not found"));

        // Product product1 = productRepository.findById(1L)
        // .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // Product product2 = productRepository.findById(2L)
        // .orElseThrow(() -> new IllegalArgumentException("Product not found"));

      }
    };
  }

}
