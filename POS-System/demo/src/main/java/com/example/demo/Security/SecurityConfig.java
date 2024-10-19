// package com.example.demo.Security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.util.matcher.AndRequestMatcher;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// @Configuration
// public class SecurityConfig {

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// http.authorizeRequests().requestMatchers(new AntPathRequestMatcher("/signup", "POST"))
// .permitAll() // Allow signup without authentication
// .anyRequest().authenticated().and().formLogin().loginPage("/api/pos/user").permitAll().and()
// .logout().permitAll();

// return http.build();
// }

// }
package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable() // Disable CSRF for APIs
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No sessions;
                                                                                    // we use JWT
        .and().authorizeRequests().antMatchers("/api/auth/**").permitAll() // Allow unauthenticated
                                                                           // access to login/signup
                                                                           // APIs
        .anyRequest().authenticated() // All other requests need authentication
        .and()
        .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Add
                                                                                                 // JWT
                                                                                                 // filter

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter();
  }
}
