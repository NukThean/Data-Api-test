package com.example.demo.purchase_order_item;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/purchase/order/item")
public class PurchaseItemController {
  private final PurchaseItemRepository purchaseItemRepository;

  @Autowired
  public PurchaseItemController(PurchaseItemRepository purchaseItemRepository) {

    this.purchaseItemRepository = purchaseItemRepository;
  }

  @GetMapping
  public List<PurchaseItem> getPurchaseItem() {
    return purchaseItemRepository.findAll();
  }

  @PostMapping
  public void addPurchaseItem(PurchaseItem purchaseItem) {
    purchaseItemRepository.save(purchaseItem);
  }

  @DeleteMapping(path = "{purchaseItemId}")
  public void deletePurchaseItem(@PathVariable("purchaseItemId") Long purchaseItemId) {
    purchaseItemRepository.deleteById(purchaseItemId);
  }

}
