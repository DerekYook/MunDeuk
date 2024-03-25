package com.together.MunDeuk.web.Member.service;

import com.together.MunDeuk.web.Member.entity.Member;
import com.together.MunDeuk.web.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailServiceImpl implements UserDetailsService {
  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
//    if ("test@test.com".equals(username)) {
//      return User.withDefaultPasswordEncoder()
//          .username("test@test.com")
//          .password("test1234")
//          .roles("User")
//          .build();
//    } else if("lotto@papa.com".equals(username)) {
//      return User.withDefaultPasswordEncoder()
//          .username("lotto@papa.com")
//          .password("test1234")
//          .roles("User","Admin")
//          .build();
//    } else {
//      throw new UsernameNotFoundException("User not found with username: " + username);
//    }
    Member member = memberRepository.selectMember(username);

    return User.withUsername(member.getEmail())
        .password("{noop}"+member.getPassword())
        .roles(String.valueOf(member.getMemberAuth()))
        .build();
  }
}
