package com.example.demo.sale_price;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SalePriceRequest {

  @JsonProperty("minQuantity")
  private Integer minQuantity;

  @JsonProperty("maxQuantity")
  private Integer maxQuantity;

  @JsonProperty("sellPrice")
  private float pricePerUnit;

  @JsonProperty("effectiveDate")
  private Date effectiveDate;

  @JsonProperty("expiryDate")
  private Date expirationDate;

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

  public float getPricePerUnit() {
    return pricePerUnit;
  }

  public void setPricePerUnit(float pricePerUnit) {
    this.pricePerUnit = pricePerUnit;
  }

  public Date getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }
}
