package com.together.MunDeuk.config;

import com.together.MunDeuk.utils.CustomLoginSuccessHandler;
import com.together.MunDeuk.utils.LoginAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@ComponentScan(basePackages = {"com.together.MunDeuk.utils"})
public class SpringSecurityConfig extends AbstractHttpConfigurer {

  private final AuthenticationConfiguration authenticationConfiguration;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
    http.authenticationManager(authenticationManager);

    http
        .authorizeHttpRequests(authorizeRequest ->
                authorizeRequest
                    .requestMatchers(new AntPathRequestMatcher("/main/**")).hasRole("User")
//                .requestMatchers(new AntPathRequestMatcher("/main/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/admin/main/**")).hasRole("Admin")
                    .requestMatchers(new AntPathRequestMatcher("/login/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/loginFail/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/h2/**")).permitAll()
                    .anyRequest().permitAll()
        )
        // 필터 변경
        .addFilterAt(
            this.abstractAuthenticationProcessingFilter(authenticationManager),
            UsernamePasswordAuthenticationFilter.class)
        .headers(
            headersConfigurer ->
                headersConfigurer
                    // SameOrigin Policy
                    .frameOptions(
                        FrameOptionsConfig::sameOrigin
                    )
                    // CSP
                    .contentSecurityPolicy(policyConfig ->
                        policyConfig.policyDirectives(
                            "script-src 'self'; img-src 'self'; font-src 'self' data:; default-src 'self'; frame-src 'self'"
                        ).reportOnly()
                    )
        )
////        .formLogin(AbstractHttpConfigurer::disable)
//        .formLogin(login -> login
//                .loginPage("/login")
//                // 기본 redirect url 지정
////            .defaultSuccessUrl("/main",true)
////            .permitAll()
//                // successHandler 이용 redirect url 지정
////                .successHandler(customSuccessHandler())
//        )
//        // CSRF Disable
////        .csrf(AbstractHttpConfigurer::disable)
        .csrf(csrf -> csrf.disable());

    return http.build();
  }

  // 인증 필터
  public AbstractAuthenticationProcessingFilter abstractAuthenticationProcessingFilter(
      final AuthenticationManager authenticationManager) {
    LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter("/ajax/loginProcess", authenticationManager);
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
    return new CustomLoginSuccessHandler();
  }
}
