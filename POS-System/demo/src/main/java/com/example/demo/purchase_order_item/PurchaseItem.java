package com.example.demo.purchase_order_item;

import jakarta.persistence.*;

import com.example.demo.product.Product;
import com.example.demo.purchase_order.PurchaseOrder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "purchase_item")
public class PurchaseItem {

  @Id
  @SequenceGenerator(name = "purchase_order_item_sequence",
      sequenceName = "purchase_order_item_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_order_item_sequence")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "purchase_order_id")
  @JsonBackReference // Backward part of the reference to break the loop
  private PurchaseOrder purchaseOrder;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  @Column(name = "unit_price", nullable = false)
  private Float unitPrice;

  public PurchaseItem() {}

  public PurchaseItem(Long id, PurchaseOrder purchaseOrder, Product product, Integer quantity,
      Float unitPrice) {
    this.id = id;
    this.purchaseOrder = purchaseOrder;
    this.product = product;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
  }

  public PurchaseItem(PurchaseOrder purchaseOrder, Product product, Integer quantity,
      Float unitPrice) {
    this.purchaseOrder = purchaseOrder;
    this.product = product;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchase(PurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Float getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(Float unitPrice) {
    this.unitPrice = unitPrice;
  }

}
