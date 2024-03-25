package com.together.MunDeuk.config;

import com.together.MunDeuk.utils.CustomLoginSuccessHandler;
import com.together.MunDeuk.utils.LoginAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends AbstractHttpConfigurer {
  private final AuthenticationConfiguration authenticationConfiguration;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
    http.authenticationManager(authenticationManager);

    http
        // CSRF Disable
//        .csrf(AbstractHttpConfigurer::disable)
//        .formLogin(AbstractHttpConfigurer::disable)
        .csrf(csrf -> csrf.disable())
        .formLogin(login -> login
            .loginPage("/login")
            // 기본 redirect url 지정
//            .defaultSuccessUrl("/main",true)
//            .permitAll()
            // successHandler 이용 redirect url 지정
            .successHandler(customSuccessHandler())
        )
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
                        HeadersConfigurer.FrameOptionsConfig::sameOrigin
                    )
                    // CSP
                    .contentSecurityPolicy( policyConfig ->
                        policyConfig.policyDirectives(
                            "script-src 'self'; img-src 'self'; font-src 'self' data:; default-src 'self'; frame-src 'self'"
                        ).reportOnly()
                    )
        );

    return http.build();
  }

  // 인증 필터
  public AbstractAuthenticationProcessingFilter abstractAuthenticationProcessingFilter(final AuthenticationManager authenticationManager) {
    return new LoginAuthenticationFilter("/ajax/loginProcess", authenticationManager);
  }

  // 로그인 성공시 handler
  @Bean
  public AuthenticationSuccessHandler customSuccessHandler(){
    System.out.println("stamp##");
    return new CustomLoginSuccessHandler();
  }
}
