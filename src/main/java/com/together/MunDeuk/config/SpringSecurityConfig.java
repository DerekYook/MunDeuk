package com.together.MunDeuk.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.together.MunDeuk.web.Member.service.CustomUserDetailService;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

  // HttpSecurity를 통해 HTTP보안 설정 구성
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers("/login").permitAll()
            .requestMatchers("admin/main").hasRole("admin")
            .requestMatchers("main").hasRole("user")
            .anyRequest().authenticated()
        )
        .httpBasic(withDefaults());
    return http.build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers("/login", "/loginFail");
  }

  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .setScriptEncoding("UTF-8")
//        .addScript("/static/db/h2.sql")
//        .addScript("/static/db/insert.sql")
        .build();
  }

  @Bean
  public CustomUserDetailService users(DataSource dataSource) {
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("test@test.com")
        .password("test1234")
        .roles("User")
        .build();
    UserDetails admin = User.withDefaultPasswordEncoder()
        .username("lotto@papa.com")
        .password("test1234")
        .roles("User","Admin")
        .build();
    // DB에서
    CustomUserDetailService users = new CustomUserDetailService();
    users.loadUserByUsername(user.getUsername());
    users.loadUserByUsername(admin.getUsername());
    return users;
  }

  // todo : 인가된 사용자 검증
  // 사용자 등록(하드코딩)해서 검증
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("user")
        .password("123412341234")
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user);
  }

  // DB에 등록된 사용자 검증
//  @Bean
//  public
}
