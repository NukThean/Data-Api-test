package com.example.demo.product;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  int countByVendorId(Long vendorId);

  int countByCategoryId(Long categoryId);

  @Query("SELECT p FROM Product p WHERE p.deletedAt IS NULL")
  List<Product> findAllActive();


  @Query("SELECT p FROM Product p WHERE p.id = :id AND p.deletedAt IS NULL")
  Product findActiveById(@Param("id") Long id);
}
