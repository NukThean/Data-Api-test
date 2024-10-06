package com.example.demo.vendor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendor")
public class Vendor {

  @Id
  @SequenceGenerator(name = "vendor_sequence", sequenceName = "vendor_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendor_sequence")
  private Long id;

  @Column(name = "vendor_name", length = 100, nullable = false)
  private String name;

  @Column(name = "vendor_image_url", length = 255, nullable = true)
  private String image;

  @Column(name = "is_active", nullable = false)
  private boolean isActive;

  @Column(name = "location", nullable = true)
  private String location;

  @Column(name = "vendor_description", length = 500, nullable = true)
  private String description;

  public Vendor() {}

  public Vendor(Long id, String name, String image, boolean isActive, String location,
      String description) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.isActive = isActive;
    this.location = location;
    this.description = description;
  }

  public Vendor(String name, String image, boolean isActive, String location, String description) {
    this.name = name;
    this.image = image;
    this.isActive = isActive;
    this.location = location;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDescription() {
    return description;
  }
}

