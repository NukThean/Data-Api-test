package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("api/pos/user")
public class UserController {

  public final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getUsers() {
    return userService.getUsers();
  }

  @PostMapping
  public void addUser(@RequestBody User user) throws NoSuchAlgorithmException {
    userService.addUser(user);
  }


  @DeleteMapping(path = "{userId}")
  public void deleteUser(@PathVariable("userId") Long userId) {
    userService.deleteUser(userId);
  }

}
