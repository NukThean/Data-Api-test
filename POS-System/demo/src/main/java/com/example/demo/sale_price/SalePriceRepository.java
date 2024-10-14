package com.example.demo.sale_price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalePriceRepository extends JpaRepository<SalePrice, Long> {
}
