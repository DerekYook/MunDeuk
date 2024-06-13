package com.together.MunDeuk.config;

import com.together.MunDeuk.web.Member.service.CustomUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class AuthenticationProviderConfig {

  private final CustomUserDetailService customUserDetailService;

  public AuthenticationProviderConfig(CustomUserDetailService customUserDetailService) {
    log.info("---- AuthenticationManagerConfig Web ----");
    this.customUserDetailService = customUserDetailService;
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(customUserDetailService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
