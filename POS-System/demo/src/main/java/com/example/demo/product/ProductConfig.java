package com.example.demo.product;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.vendor.Vendor;
import com.example.demo.vendor.VendorRepository;

@Configuration
public class ProductConfig {

  @Bean
  CommandLineRunner commandLineRunner(ProductRepository productRepository,
      VendorRepository vendorRepository) {
    return args -> {
      long count = productRepository.count();
      if (count == 0) {
        // Get today's date
        Date currentDate = new Date();

        Date createdAt = currentDate;
        Date updatedAt = currentDate;

        Vendor vendor =
            new Vendor("Logiech Cambodia", "Image/Logitech", true, "Monivong Street #13A", null);

        vendorRepository.save(vendor);

        Product G102 = new Product("G102", "Logitech G102", 10, "Mouse", "200-8000 DPI", 15.00f,
            19.00f, "Image/G102", createdAt, updatedAt, true, vendor);

        productRepository.saveAll(List.of(G102));
      } ;
    };
  }
}
