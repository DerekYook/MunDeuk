package com.together.MunDeuk.web.Member.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if ("test@test.com".equals(username)) {
      return User.withDefaultPasswordEncoder()
          .username("test@test.com")
          .password("test1234")
          .roles("User")
          .build();
    } else if("lotto@papa.com".equals(username)) {
      return User.withDefaultPasswordEncoder()
          .username("lotto@papa.com")
          .password("test1234")
          .roles("User","Admin")
          .build();
    } else {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
  }
}
