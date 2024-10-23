package com.example.demo.product;


import java.util.List;
import com.example.demo.sale_price.SalePriceRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRequest {
  @JsonProperty("code")
  private String productCode;

  @JsonProperty("name")
  private String productName;

  @JsonProperty("quantity")
  private int quantity;

  @JsonProperty("description")
  private String description;

  @JsonProperty("image")
  private String imageUrl;

  @JsonProperty("vendor")
  private Long vendorId;

  @JsonProperty("category")
  private Long categoryId;

  @JsonProperty("isActive")
  private boolean isActive;

  @JsonProperty("salePrice")
  private List<SalePriceRequest> salePrices;


  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Long getVendorId() {
    return vendorId;
  }

  public void setVendorId(Long vendorId) {
    this.vendorId = vendorId;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public List<SalePriceRequest> getSalePrices() {
    return salePrices;
  }

  public void setSalePrices(List<SalePriceRequest> salePrices) {
    this.salePrices = salePrices;
  }

  public boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }
}

