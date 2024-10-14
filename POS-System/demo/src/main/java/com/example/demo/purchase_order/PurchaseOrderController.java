package com.example.demo.purchase_order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("api/purchase/order")
public class PurchaseOrderController {

  private final PurchaseOrderService purchaseOrderService;



  @Autowired
  public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
    this.purchaseOrderService = purchaseOrderService;

  }

  @GetMapping
  public List<PurchaseOrder> getPurchase() {
    return purchaseOrderService.getPurchase();
  }


  @PostMapping
  public void addPurchase(@RequestBody PurchaseOrder purchaseOrder) {
    purchaseOrderService.addPurchase(purchaseOrder);
  }

  @DeleteMapping(path = "{purchaseOrderId}")
  public void deletePurchase(@PathVariable("purchaseOrderId") long purchaseOrderId) {
    purchaseOrderService.deletePurchase(purchaseOrderId);
  }
}
