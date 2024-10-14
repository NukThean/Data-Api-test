package com.example.demo.purchase_order;

import java.util.Date;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.product.ProductRepository;
import com.example.demo.purchase_order_item.PurchaseItem;
import com.example.demo.purchase_order_item.PurchaseItemRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.vendor.Vendor;
import com.example.demo.vendor.VendorRepository;
import com.example.demo.product.Product;

import java.util.List;

@Configuration
public class PurchaseOrderConfig {

  @Bean
  CommandLineRunner CommandLineRunnerPurchase(PurchaseOrderRepository purchaseOrderRepository,
      ProductRepository productRepository, UserRepository userRepository,
      VendorRepository vendorRepository, PurchaseItemRepository purchaseItemRepository) {

    return args -> {
      long count = purchaseOrderRepository.count();
      if (count < 1) {


        Product product1 = productRepository.findById(1L)
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Product product2 = productRepository.findById(2L)
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        // Fetch the Vendor
        Vendor vendor = vendorRepository.findById(1L)
            .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        // Fetch the User
        User user = userRepository.findById(1L)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Create the PurchaseOrder
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setVendor(vendor);
        purchaseOrder.setOrderDate(new Date());
        purchaseOrder.setUser(user);

        // Save the PurchaseOrder (which will also save the PurchaseItems due to CascadeType.ALL)
        purchaseOrderRepository.save(purchaseOrder);

        PurchaseItem purchaseItem1 = new PurchaseItem(purchaseOrder, product1, 10, 10f);
        PurchaseItem purchaseItem2 = new PurchaseItem(purchaseOrder, product2, 10, 30f);
        purchaseItemRepository.save(purchaseItem1);
        purchaseItemRepository.save(purchaseItem2);

        // Link the PurchaseItems to the PurchaseOrder
        purchaseOrder.setItems(List.of(purchaseItem1, purchaseItem2));

      }
    };
  }
}
