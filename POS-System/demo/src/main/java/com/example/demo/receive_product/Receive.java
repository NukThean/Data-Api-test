package com.example.demo.receive_product;

import com.example.demo.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "receive_product")
public class Receive {

  @Id
  @SequenceGenerator(name = "sale_price_sequence", sequenceName = "sale_price_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_price_sequence")
  private Long id;

  // @OneToMany
  // @JoinColumn(name = "product")
  // private Product product;
}
