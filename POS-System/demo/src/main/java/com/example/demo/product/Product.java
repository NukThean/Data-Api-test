package com.example.demo.product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import com.example.demo.vendor.Vendor;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.demo.product_category.Category;
import com.example.demo.sale_price.SalePrice;


@Entity
@Table(name = "product")
public class Product {

  @Id
  @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
  private Long id;

  @Column(name = "product_code", length = 20, nullable = true, unique = true)
  private String code;

  @Column(name = "product_name", length = 100, nullable = false)
  private String name;

  @Column(name = "product_quantity", nullable = true)
  private Integer quantity;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = true)
  private Category category;

  @Column(name = "product_description", length = 500, nullable = true)
  private String description;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL,
      orphanRemoval = true)
  @JsonManagedReference // Breaks circular reference
  private List<SalePrice> salePrices; // One product can have multiple sale prices

  @Column(name = "product_image_url", length = 255, nullable = true)
  private String image;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false, updatable = false)
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_at", nullable = false)
  private Date updatedAt;

  @Column(name = "is_active", nullable = false)
  private boolean isActive = true; // Track if product is active (continue or discontinue)

  @ManyToOne // Many products can belong to one vendor
  @JoinColumn(name = "vendor_id", nullable = true) // Foreign key in the product table
  private Vendor vendor;

  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;

  public Product() {}

  public Product(Long id, String code, String name, Integer quantity, Category category,
      String description, List<SalePrice> salePrices, String image, Date createdAt, Date updatedAt,
      boolean isActive, Vendor vendor, LocalDateTime deletedAt) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.quantity = quantity;
    this.category = category;
    this.description = description;
    this.salePrices = salePrices;
    this.image = image;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.isActive = isActive;
    this.vendor = vendor;
    this.deletedAt = deletedAt;
  }

  public Product(String code, String name, Integer quantity, Category category, String description,
      List<SalePrice> salePrices, String image, Date createdAt, Date updatedAt, boolean isActive,
      Vendor vendor, LocalDateTime deletedAt) {
    this.code = code;
    this.name = name;
    this.quantity = quantity;
    this.category = category;
    this.description = description;
    this.salePrices = salePrices;
    this.image = image;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.isActive = isActive;
    this.vendor = vendor;
    this.deletedAt = deletedAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<SalePrice> getSalePrices() {
    return salePrices;
  }

  public void setSalePrice(List<SalePrice> salePrices) {
    this.salePrices = salePrices;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  public Vendor getVendor() {
    return vendor;
  }

  public void setVendor(Vendor vendor) {
    this.vendor = vendor;
  }

  public LocalDateTime getdeletedAt() {
    return deletedAt;
  }

  public void setdeletedAt(LocalDateTime deletedAt) {
    this.deletedAt = deletedAt;
  }

  @Override
  public String toString() {
    return "Product [id=" + id + ", name=" + name + ", quantity=" + quantity + ", category="
        + category + ", description=" + description + ", salePrice=" + salePrices + ", image="
        + image + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", isActive=" + isActive
        + ", vendor=" + vendor + "deletedAt=" + deletedAt + "]";
  }
}
