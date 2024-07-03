package com.together.MunDeuk.config;

import com.together.MunDeuk.utils.CustomAuthenticationFailureHandler2;
import com.together.MunDeuk.utils.CustomLoginSuccessHandler2;
import com.together.MunDeuk.utils.CustomOauth2LoginSuccessHandler2;
import com.together.MunDeuk.utils.JwtAuthenticationFilter2;
import com.together.MunDeuk.utils.JwtAuthenticationProvider;
import com.together.MunDeuk.utils.LoginAuthenticationFilter;
import com.together.MunDeuk.utils.OAuth2LoginAuthenticationProvider;
import com.together.MunDeuk.web.OAuth2.service.CustomOAuth2UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
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
@Slf4j
public class SpringSecurityConfig2{
  private final AuthenticationConfiguration authenticationConfiguration;
  private final CustomOAuth2UserService customOAuth2UserService;
  private final JwtAuthenticationProvider jwtAuthenticationProvider;
  private final OAuth2LoginAuthenticationProvider oAuth2LoginAuthenticationProvider;
//  private final DaoAuthenticationProvider daoAuthenticationProvider;
//
  // JwtAuthenticationProvider 주입
  @Autowired
  public void configureAuthentication(AuthenticationManagerBuilder builder){
    builder.authenticationProvider(jwtAuthenticationProvider);
    builder.authenticationProvider(oAuth2LoginAuthenticationProvider);
  }

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
  public AuthenticationSuccessHandler customSuccessHandler() {
    return new CustomLoginSuccessHandler2();
  }

  // OAuth2 로그인 성공시 handler
  @Bean
  public CustomOauth2LoginSuccessHandler2 customOauth2LoginSuccessHandler() {
    return new CustomOauth2LoginSuccessHandler2();
  }

  // OAuth2 로그인 실패시 handler
  @Bean
  public CustomAuthenticationFailureHandler2 customAuthenticationFailureHandler(){
    return new CustomAuthenticationFailureHandler2();
  }

  @Bean
  public JwtAuthenticationFilter2 jwtVerifyFilter() {
    return new JwtAuthenticationFilter2();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
    http.authenticationManager(authenticationManager);

    http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()));

    http.csrf(AbstractHttpConfigurer::disable);

    http.sessionManagement(httpSecuritySessionManagementConfigurer -> {
      httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.NEVER);
    });

    // request URL에 대한 권한 확인
    http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
        authorizationManagerRequestMatcherRegistry.anyRequest().permitAll());

    http.addFilterBefore(jwtVerifyFilter(), UsernamePasswordAuthenticationFilter.class);

    // Form 로그인 설정
    http.formLogin(httpSecurityFormLoginConfigurer -> {
      log.info("----Configuring Form Login----");
      httpSecurityFormLoginConfigurer
          .loginPage("/login")
          .successHandler(customSuccessHandler())
          .failureHandler(customAuthenticationFailureHandler())
          ;
      // 로컬에서만 Filter적용
      http.addFilterAt(this.abstractAuthenticationProcessingFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
    });
    // oauth인증
    http.oauth2Login(httpSecurityOAuth2LoginConfigurer -> {
        log.info("----Configuring Oauth2 Login----");
        httpSecurityOAuth2LoginConfigurer                              
            .loginPage("/login")
            .successHandler(customOauth2LoginSuccessHandler())
            .failureHandler(customAuthenticationFailureHandler())
            // 사용자 인증
            .userInfoEndpoint(userInfoEndpointConfig ->
                userInfoEndpointConfig.userService(customOAuth2UserService))
        ;
    });
    return http.build();
  }

  // 인증 필터
  public AbstractAuthenticationProcessingFilter abstractAuthenticationProcessingFilter(
      // 토큰 정보 추가
      final AuthenticationManager authenticationManager) {
    // 토큰 정보를 이용해 사용자 정보 식별
    LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter("/ajax/loginProcess", authenticationManager);
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

}