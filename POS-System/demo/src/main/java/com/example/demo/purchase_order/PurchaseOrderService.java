package com.example.demo.purchase_order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.demo.product.ProductRepository;
import com.example.demo.user.UserRepository;
import com.example.demo.vendor.VendorRepository;

@Service
public class PurchaseOrderService {

  private final PurchaseOrderRepository purchaseOrderRepository;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;
  private final VendorRepository vendorRepository;

  @Autowired
  public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository,
      ProductRepository productRepository, UserRepository userRepository,
      VendorRepository vendorRepository) {
    this.purchaseOrderRepository = purchaseOrderRepository;
    this.productRepository = productRepository;
    this.userRepository = userRepository;
    this.vendorRepository = vendorRepository;
  }

  public List<PurchaseOrder> getPurchase() {
    return purchaseOrderRepository.findAll();
  }

  public void addPurchase(PurchaseOrder purchaseOrder) {
    purchaseOrderRepository.save(purchaseOrder);
  }

  public void deletePurchase(Long purchaseId) {
    purchaseOrderRepository.deleteById(purchaseId);
  }
}
