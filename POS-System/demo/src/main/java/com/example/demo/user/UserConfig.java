package com.example.demo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class UserConfig {

  @Bean
  CommandLineRunner commandLineRunnerUser(UserRepository userRepository) {
    return args -> {
      long count = userRepository.count();
      if (count == 0) {
        Date currentDate = new Date();
        User user = new User("Sana", "tt@gmail.com", "admin", "123", null, false, currentDate);

        userRepository.save(user);
      }
    };
  }
}
