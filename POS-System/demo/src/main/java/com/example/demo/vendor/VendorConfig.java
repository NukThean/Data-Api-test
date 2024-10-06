package com.example.demo.vendor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VendorConfig {

  @Bean
  CommandLineRunner commandLineRunner1(VendorRepository vendorRepository) {
    return args -> {
      long count = vendorRepository.count();
      if (count == 0) {
        // Vendor vendor =
        // new Vendor("Logiech Cambodia", "Image/Logitech", true, "Monivong Street #13A", null);
        // vendorRepository.save(vendor);
      }
    };
  }
}
