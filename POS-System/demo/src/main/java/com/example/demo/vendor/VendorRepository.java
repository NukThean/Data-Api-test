package com.example.demo.vendor;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.product.Product;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

  Optional<Product> findVendorById(Long Id);
}
