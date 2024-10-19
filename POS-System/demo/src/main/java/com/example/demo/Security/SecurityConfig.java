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
package com.example.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        // ...
        .csrf((csrf) -> csrf.csrfTokenRepository(new HttpSessionCsrfTokenRepository()))
        .addFilterAfter(new CsrfHeaderFilter(), UsernamePasswordAuthenticationFilter.class); // Add
                                                                                             // custom
                                                                                             // filter
    return http.build();
  }
}
