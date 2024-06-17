package com.together.MunDeuk.config;

import com.together.MunDeuk.utils.JwtAuthenticationProvider;
import com.together.MunDeuk.web.Member.service.CustomUserDetailService;
import com.together.MunDeuk.web.OAuth2.service.CustomOAuth2UserService;
import com.together.MunDeuk.utils.OAuth2LoginAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Slf4j
//@Configuration
//@EnableWebSecurity
public class AuthenticationProviderConfig {
//
//  private final CustomUserDetailService customUserDetailService;
//  private final CustomOAuth2UserService customOAuth2UserService;
//
//  public AuthenticationProviderConfig(CustomUserDetailService customUserDetailService,
//      CustomOAuth2UserService customOAuth2UserService) {
//    log.info("---- AuthenticationManagerConfig Web ----");
//    this.customUserDetailService = customUserDetailService;
//    log.info("---- AuthenticationManagerConfig OAuth2 ----");
//    this.customOAuth2UserService = customOAuth2UserService;
//  }
//
//  @Bean
//  public DaoAuthenticationProvider daoAuthenticationProvider() {
//    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//    provider.setUserDetailsService(customUserDetailService);
////    provider.setPasswordEncoder(passwordEncoder());
//    return provider;
//  }
//
////  @Bean
////  public PasswordEncoder passwordEncoder() {
////    return new BCryptPasswordEncoder();
////  }
////
////  public AuthenticationProviderConfig(CustomUserDetailService customUserDetailService,
////      CustomOAuth2UserService customOAuth2UserService) {
////    this.customUserDetailService = customUserDetailService;
////    this.customOAuth2UserService = customOAuth2UserService;
////  }
//
////  @Bean
////  public AuthenticationProvider jwtAuthenticationProvider() {
////    return new JwtAuthenticationProvider();
////  }
//
//  @Bean
//  public AuthenticationProvider oauth2AuthenticationProvider() {
//    return new OAuth2LoginAuthenticationProvider(customOAuth2UserService);
//  }
//
//  public CustomOAuth2UserService getCustomOAuth2UserService() {
//    return customOAuth2UserService;
//  }
//
//  public CustomUserDetailService getCustomUserDetailService() {
//    return customUserDetailService;
//  }
}
