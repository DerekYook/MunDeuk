package com.together.MunDeuk.config;

import com.together.MunDeuk.utils.CustomLoginSuccessHandler2;
import com.together.MunDeuk.utils.JwtAuthenticationFilter2;
import com.together.MunDeuk.utils.JwtTokenizer;
import com.together.MunDeuk.utils.LoginAuthenticationFilter;
import com.together.MunDeuk.web.OAuth2.service.CustomOAuth2UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@EnableMethodSecurity
@Configuration
public class SpringSecurityConfig2{
  // todo : oauth2 use
  private final CustomOAuth2UserService customOAuth2UserService;

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();

    corsConfiguration.setAllowedOriginPatterns(List.of("*"));
    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
    corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
    corsConfiguration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration); // 모든 경로에 대해서 CORS 설정을 적용

    return source;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CustomLoginSuccessHandler2 commonLoginSuccessHandler() {
    return new CustomLoginSuccessHandler2();
  }

//  @Bean
//  public CommonLoginFailHandler commonLoginFailHandler() {
//    return new CommonLoginFailHandler();
//  }

  @Bean
  public JwtAuthenticationFilter2 jwtVerifyFilter() {
    return new JwtAuthenticationFilter2();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()));

    http.csrf(AbstractHttpConfigurer::disable);

    http.sessionManagement(httpSecuritySessionManagementConfigurer -> {
      httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.NEVER);
    });

    http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
        authorizationManagerRequestMatcherRegistry.anyRequest().permitAll());

    http.addFilterBefore(jwtVerifyFilter(), UsernamePasswordAuthenticationFilter.class);

    http.formLogin(httpSecurityFormLoginConfigurer -> {httpSecurityFormLoginConfigurer
        .loginPage("/login")
        .successHandler(commonLoginSuccessHandler());
//        .failureHandler(commonLoginFailHandler());
    });

    http.oauth2Login(httpSecurityOAuth2LoginConfigurer ->
        httpSecurityOAuth2LoginConfigurer.loginPage("/login")
            .successHandler(commonLoginSuccessHandler())
            .userInfoEndpoint(userInfoEndpointConfig ->
                userInfoEndpointConfig.userService(customOAuth2UserService)));

    return http.build();
  }

  // 인증 필터
  public AbstractAuthenticationProcessingFilter abstractAuthenticationProcessingFilter(
//      final AuthenticationManager authenticationManager) {
      // 토큰 정보 추가
      final AuthenticationManager authenticationManager, JwtTokenizer jwtTokenizer) {
    LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter("/ajax/loginProcess", authenticationManager);
//    loginAuthenticationFilter.setAuthenticationSuccessHandler(customSuccessHandler());
    // Handler에 토큰 정보 추가
    loginAuthenticationFilter.setAuthenticationSuccessHandler(customSuccessHandler());
    // Rest API 방식을 사용하기 위해 추가
    loginAuthenticationFilter.setSecurityContextRepository(
        new DelegatingSecurityContextRepository(
            new RequestAttributeSecurityContextRepository(),
            new HttpSessionSecurityContextRepository()
        ));
    return loginAuthenticationFilter;
  }

  // 로그인 성공시 handler
  @Bean
  public AuthenticationSuccessHandler customSuccessHandler() {
    return new CustomLoginSuccessHandler2();
  }
}