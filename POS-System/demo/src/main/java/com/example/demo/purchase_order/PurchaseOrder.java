package com.example.demo.purchase_order;

import com.example.demo.product.Product;
import com.example.demo.purchase_order_item.PurchaseItem;
import com.example.demo.user.User;
import com.example.demo.vendor.Vendor;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {

  @Id
  @SequenceGenerator(name = "purchase_order_sequence", sequenceName = "purchase_order_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_order_sequence")
  private Long id;

  @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
  @JsonManagedReference // Breaks circular reference
  private List<PurchaseItem> items;

  @ManyToOne
  @JoinColumn(name = "vendor_id")
  private Vendor vendor;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "order_date", nullable = false, updatable = false)
  private Date orderDate;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public PurchaseOrder() {}

  public PurchaseOrder(Long id, List<PurchaseItem> items, Vendor vendor, Date orderDate,
      User user) {
    this.id = id;
    this.items = items;
    this.vendor = vendor;
    this.orderDate = orderDate;
    this.user = user;
  }

  public PurchaseOrder(List<PurchaseItem> items, Vendor vendor, Date orderDate, User user) {
    this.items = items;
    this.vendor = vendor;
    this.orderDate = orderDate;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<PurchaseItem> getItems() {
    return items;
  }

  public void setItems(List<PurchaseItem> items) {
    this.items = items;
  }

  public Vendor getVendor() {
    return vendor;
  }

  public void setVendor(Vendor vendor) {
    this.vendor = vendor;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "PurchaseOrder [id=" + id + ", items=" + items + ", vendor=" + vendor + ", orderDate="
        + orderDate + ", user=" + user + "]";
  }

}
