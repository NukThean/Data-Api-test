package com.example.demo.sale_price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.product.ProductRepository;
import java.util.List;

@Service
public class SalePriceService {

  private final SalePriceRepository salePriceRepository;
  private final ProductRepository productRepository;

  @Autowired
  public SalePriceService(SalePriceRepository salePriceRepository,
      ProductRepository productRepository) {
    this.salePriceRepository = salePriceRepository;
    this.productRepository = productRepository;
  }

  public List<SalePrice> getSalePrices() {
    return salePriceRepository.findAll();
  }

  public void addSalePrice(SalePrice salePrice) {
    salePriceRepository.save(salePrice);
  }

  public void deleteSalePrice(Long salePriceId) {
    boolean exists = salePriceRepository.existsById(salePriceId);
    if (!exists) {
      throw new IllegalStateException("SalePrice with id " + salePriceId + " does not exists");
    }
    salePriceRepository.deleteById(salePriceId);
  }
}
