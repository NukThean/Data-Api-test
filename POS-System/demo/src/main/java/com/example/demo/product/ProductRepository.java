package com.example.demo.product;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  int countByVendorId(Long vendorId);

  int countByCategoryId(Long categoryId);
}
