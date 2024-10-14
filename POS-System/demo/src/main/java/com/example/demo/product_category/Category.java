package com.example.demo.product_category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_category")
public class Category {
  @Id
  @SequenceGenerator(name = "product_category_sequence", sequenceName = "product_category_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_category_sequence")
  private Long id;

  @Column(name = "category_name", length = 100, nullable = false)
  private String name;

  public Category() {}

  public Category(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Category(String name) {
    this.name = name;
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
}
