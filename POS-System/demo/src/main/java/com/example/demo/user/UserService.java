package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  public final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public void addUser(User user) {
    userRepository.save(user);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}
