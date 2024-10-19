package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {

  @Value("${security.pepper}")
  private String pepper;

  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public final UserRepository userRepository;

  // Generate a random salt value
  public String generateSalt() throws NoSuchAlgorithmException {
    SecureRandom sr = SecureRandom.getInstance("SHA-256");
    byte[] salt = new byte[16];
    sr.nextBytes(salt);
    return Base64.getEncoder().encodeToString(salt);
  }

  // Hash the password with salt and pepper
  public String hashPassword(String rawPassword, String salt) {
    // Combine password, salt, and pepper for hashing
    String saltedAndPepperedPassword = rawPassword + salt + pepper;
    return passwordEncoder.encode(saltedAndPepperedPassword);
  }

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public void addUser(User user) throws NoSuchAlgorithmException {
    String salt = generateSalt();

    // Hash the user's password using the salt and pepper
    String hashedPassword = hashPassword(user.getPassword(), salt);

    // Set the hashed password and salt in the user object
    user.setPassword(hashedPassword);
    user.setSaltValue(salt);

    userRepository.save(user);
  }


  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}
