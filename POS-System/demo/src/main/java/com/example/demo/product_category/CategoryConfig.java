package com.example.demo.product_category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.product.ProductRepository;

@Configuration
public class CategoryConfig {


  @Bean
  CommandLineRunner commandLineRunnerCategory(ProductRepository productRepository,
      CategoryRepository categoryRepository) {
    return args -> {
      // long count = categoryRepository.count();

      // if (count == 0) {
      // Category category = new Category("Mouse");
      // categoryRepository.save(category);
      // }
    };
  }
}
