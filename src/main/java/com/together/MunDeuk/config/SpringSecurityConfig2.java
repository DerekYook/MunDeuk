package com.together.MunDeuk.config;

import com.together.MunDeuk.utils.CaptchaUtil;
import com.together.MunDeuk.utils.CookieUtil;
import com.together.MunDeuk.utils.CustomAuthenticationFailureHandler2;
import com.together.MunDeuk.utils.CustomLoginFailHandler2;
import com.together.MunDeuk.utils.CustomLoginSuccessHandler2;
import com.together.MunDeuk.utils.CustomOauth2LoginSuccessHandler2;
import com.together.MunDeuk.utils.JwtAuthenticationFilter2;
import com.together.MunDeuk.utils.JwtAuthenticationProvider;
import com.together.MunDeuk.utils.JwtTokenizer2;
import com.together.MunDeuk.utils.LoginAuthenticationFilter;
import com.together.MunDeuk.utils.OAuth2LoginAuthenticationProvider;
import com.together.MunDeuk.web.Member.service.MemberService;
import com.together.MunDeuk.web.OAuth2.service.CustomOAuth2UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@EnableMethodSecurity
@Configuration
@Slf4j
public class SpringSecurityConfig2{

  private final MemberService memberService;
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

  private final JwtTokenizer2 jwtTokenizer2;
  private final CookieUtil cookieUtil;

  private final CaptchaUtil captchaUtil;

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
    return new CustomLoginSuccessHandler2(cookieUtil, jwtTokenizer2);
  }

  @Bean
  public CustomLoginFailHandler2 customFailureHandler() {
    return new CustomLoginFailHandler2();
  }

  // OAuth2 로그인 성공시 handler
  @Bean
  public CustomOauth2LoginSuccessHandler2 customOauth2LoginSuccessHandler() {
    return new CustomOauth2LoginSuccessHandler2(cookieUtil, jwtTokenizer2);
  }

  // OAuth2 로그인 실패시 handler
  @Bean
  public CustomAuthenticationFailureHandler2 customAuthenticationFailureHandler(){
    return new CustomAuthenticationFailureHandler2(cookieUtil);
  }

  @Bean
  public JwtAuthenticationFilter2 jwtVerifyFilter() {
    return new JwtAuthenticationFilter2();
  }

  @Bean
  public HttpSessionCsrfTokenRepository sessionCsrfRepository() {
    HttpSessionCsrfTokenRepository csrfRepository = new HttpSessionCsrfTokenRepository();

    // HTTP 헤더에서 토큰을 인덱싱하는 문자열 설정
    csrfRepository.setHeaderName("X-CSRF-TOKEN");
    // URL 파라미터에서 토큰에 대응되는 변수 설정
    csrfRepository.setParameterName("_csrf");
    // 세션에서 토큰을 인덱싱 하는 문자열을 설정. 기본값이 무척 길어서 오버라이딩 하는 게 좋아요.
    // 기본값: "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN"
    csrfRepository.setSessionAttributeName("CSRF_TOKEN");

    return csrfRepository;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
    http.authenticationManager(authenticationManager);

    http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()));

    http.csrf(AbstractHttpConfigurer::disable);
    http.csrf((csrf) -> csrf
        .csrfTokenRepository(sessionCsrfRepository()));

//    // h2 볼때만 활성화
//    http.headers().frameOptions().disable();

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
          .failureHandler(customFailureHandler())
          ;
      // 로컬에서만 Filter적용
      http.addFilterAt(this.abstractAuthenticationProcessingFilter(authenticationManager, captchaUtil, memberService), UsernamePasswordAuthenticationFilter.class);
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
      final AuthenticationManager authenticationManager, final CaptchaUtil captchaUtil, final MemberService memberService) {
    // 토큰 정보를 이용해 사용자 정보 식별
    LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter("/ajax/loginProcess", authenticationManager, captchaUtil, memberService);
    // Handler에 토큰 정보 추가
    loginAuthenticationFilter.setAuthenticationSuccessHandler(customSuccessHandler());
    loginAuthenticationFilter.setAuthenticationFailureHandler(customFailureHandler());
    // Rest API 방식을 사용하기 위해 추가
    loginAuthenticationFilter.setSecurityContextRepository(
        new DelegatingSecurityContextRepository(
            new RequestAttributeSecurityContextRepository(),
            new HttpSessionSecurityContextRepository()
        ));
    return loginAuthenticationFilter;
  }

}