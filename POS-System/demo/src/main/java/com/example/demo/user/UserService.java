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

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
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

  // Generate a random salt value
  public String generateSalt() throws NoSuchAlgorithmException {
    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
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

  // Find user by username
  public User findByUsername(String username) {
    return userRepository.findByName(username);
  }

  // Verify the password by combining raw password, salt, and pepper, then comparing it with the
  // stored hashed password
  public boolean verifyPassword(String rawPassword, String salt, String hashedPassword) {
    // Combine raw password, salt, and pepper in the same way as during hashing
    String saltedAndPepperedPassword = rawPassword + salt + pepper;
    // Use the password encoder to check if the hashed version matches the stored hash
    return passwordEncoder.matches(saltedAndPepperedPassword, hashedPassword);
  }

}
