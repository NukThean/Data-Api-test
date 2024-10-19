package com.example.demo.user;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "users")
public class User {

  @Id
  @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
  private Long id;

  @Column(name = "username", length = 100, nullable = false)
  private String name;

  @Column(name = "user_email", length = 100, nullable = false, unique = true)
  private String email;

  @Column(name = "user_role", length = 100, nullable = false)
  private String role;

  @Column(name = "user_password", length = 100, nullable = false)
  private String password;

  @Column(name = "salt_value", length = 100, nullable = true)
  private String saltValue;

  @Column(name = "is_blocked", nullable = false)
  private boolean isBlocked;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "last_login", nullable = true)
  private Date lastLogin;

  public User() {}

  public User(Long id, String name, String email, String role, String password, String saltValue,
      boolean isBlocked, Date lastLogin) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.role = role;
    this.password = password;
    this.saltValue = saltValue;
    this.isBlocked = isBlocked;
    this.lastLogin = lastLogin;
  }

  public User(String name, String email, String role, String password, String saltValue,
      boolean isBlocked, Date lastLogin) {
    this.name = name;
    this.email = email;
    this.role = role;
    this.password = password;
    this.saltValue = saltValue;
    this.isBlocked = isBlocked;
    this.lastLogin = lastLogin;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSaltValue() {
    return saltValue;
  }

  public void setSaltValue(String saltValue) {
    this.saltValue = saltValue;
  }

  public boolean isBlocked() {
    return isBlocked;
  }

  public void setBlocked(boolean blocked) {
    isBlocked = blocked;
  }

  public Date getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }
}
