package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


  @PostMapping("login")
  public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
    User user = userService.findByUsername(loginRequest.getUsername());

    if (user != null) {
      // Fetch salt value from the user entity
      String salt = user.getSaltValue();
      System.out.println(user + " " + salt + " " + loginRequest.getPassword());

      // Verify password with salt
      if (userService.verifyPassword(loginRequest.getPassword(), salt, user.getPassword())) {
        return ResponseEntity.ok("Login successful");
      }
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
  }

  @GetMapping("login")
  public ResponseEntity<String> getLogin() {
    // This could be used to check if the login page or CSRF token is accessible
    return ResponseEntity.ok("Login endpoint is working.");
  }


}
