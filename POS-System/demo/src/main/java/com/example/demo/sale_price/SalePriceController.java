package com.example.demo.sale_price;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/pos/product/price")
public class SalePriceController {

  private final SalePriceService salePriceService;

  @Autowired
  public SalePriceController(SalePriceService salePriceService) {
    this.salePriceService = salePriceService;
  }

  @GetMapping
  public List<SalePrice> getSalePrices() {
    return salePriceService.getSalePrices();
  }

  @PostMapping
  public void addProduct(@RequestBody SalePrice salePrice) {
    salePriceService.addSalePrice(salePrice);
  }

  @DeleteMapping(path = "{salePriceId}")
  public void deleteSalePrice(@PathVariable("salePriceId") Long salePriceId) {
    salePriceService.deleteSalePrice(salePriceId);
  }
}
