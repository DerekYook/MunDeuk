package com.together.MunDeuk.config;

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
        .csrf(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorizeRequest ->
            authorizeRequest
                .requestMatchers(new AntPathRequestMatcher("/auth/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/h2/**")).permitAll()
        )
        .addFilterAt(
            this.abstractAuthenticationProcessingFilter(authenticationManager),
            UsernamePasswordAuthenticationFilter.class)
        .headers(
            headersConfigurer ->
                headersConfigurer
                    .frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::sameOrigin
                    )
                    .contentSecurityPolicy( policyConfig ->
                        policyConfig.policyDirectives(
                            "script-src 'self'; " + "img-src 'self'; " +
                                "font-src 'self' data:; " + "default-src 'self'; " +
                                "frame-src 'self'"
                        )
                    )
        );

    return http.build();
  }

  public AbstractAuthenticationProcessingFilter abstractAuthenticationProcessingFilter(final AuthenticationManager authenticationManager) {
    return new LoginAuthenticationFilter("/login", authenticationManager);
  }
}
