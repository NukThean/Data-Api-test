package com.example.demo.sale_price;

import com.example.demo.product.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "sale_price")
public class SalePrice {

  @Id
  @SequenceGenerator(name = "sale_price_sequence", sequenceName = "sale_price_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_price_sequence")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  @JsonBackReference // Prevents infinite recursion
  private Product product;

  @Column(name = "min_quantity", nullable = false)
  private Integer minQuantity;

  @Column(name = "max_quantity", nullable = true)
  private Integer maxQuantity;

  @Column(name = "price", nullable = false)
  private Float unit_price;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "effective_date", nullable = true)
  private Date effective_Date;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "expiration_date", nullable = true)
  private Date expiration_Date;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false, updatable = false)
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_at", nullable = false)
  private Date updatedAt;

  public SalePrice() {}

  public SalePrice(Product product, Integer minQuantity, Integer maxQuantity, Float unit_price,
      Date effective_Date, Date expiration_Date, Date createdAt, Date updatedAt) {
    this.product = product;
    this.minQuantity = minQuantity;
    this.maxQuantity = maxQuantity;
    this.unit_price = unit_price;
    this.effective_Date = effective_Date;
    this.expiration_Date = expiration_Date;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }


  public SalePrice(Long id, Product product, Integer minQuantity, Integer maxQuantity,
      Float unit_price, Date effective_Date, Date expiration_Date, Date createdAt, Date updatedAt) {
    this.id = id;
    this.product = product;
    this.minQuantity = minQuantity;
    this.maxQuantity = maxQuantity;
    this.unit_price = unit_price;
    this.effective_Date = effective_Date;
    this.expiration_Date = expiration_Date;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Integer getMinQuantity() {
    return minQuantity;
  }

  public void setMinQuantity(Integer minQuantity) {
    this.minQuantity = minQuantity;
  }

  public Integer getMaxQuantity() {
    return maxQuantity;
  }

  public void setMaxQuantity(Integer maxQuantity) {
    this.maxQuantity = maxQuantity;
  }

  public Float getUnit_price() {
    return unit_price;
  }

  public void setUnit_price(Float unit_price) {
    this.unit_price = unit_price;
  }

  public Date getEffective_Date() {
    return effective_Date;
  }

  public void setEffective_Date(Date effective_Date) {
    this.effective_Date = effective_Date;
  }

  public Date getExpiration_Date() {
    return expiration_Date;
  }

  public void setExpiration_Date(Date expiration_Date) {
    this.expiration_Date = expiration_Date;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
