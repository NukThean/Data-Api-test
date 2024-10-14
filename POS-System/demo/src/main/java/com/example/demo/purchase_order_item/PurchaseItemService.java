package com.example.demo.purchase_order_item;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.product.ProductRepository;
import com.example.demo.purchase_order.PurchaseOrderRepository;

@Service
public class PurchaseItemService {

  private final PurchaseItemRepository purchaseItemRepository;
  private final ProductRepository productRepository;
  private final PurchaseOrderRepository purchaseRepository;

  @Autowired
  public PurchaseItemService(PurchaseItemRepository purchaseItemRepository,
      ProductRepository productRepository, PurchaseOrderRepository purchaseRepository) {
    this.purchaseItemRepository = purchaseItemRepository;
    this.productRepository = productRepository;
    this.purchaseRepository = purchaseRepository;
  }

  public List<PurchaseItem> getPurchaseItem() {
    return purchaseItemRepository.findAll();
  }

  public void addPurchaseItem(PurchaseItem purchaseItem) {
    purchaseItemRepository.save(purchaseItem);
  }

  public void deletePurchaseItem(Long purchaseItemId) {
    purchaseItemRepository.deleteById(purchaseItemId);
  }

}
