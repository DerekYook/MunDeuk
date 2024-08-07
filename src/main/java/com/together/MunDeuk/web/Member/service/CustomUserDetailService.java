package com.together.MunDeuk.web.Member.service;

import com.together.MunDeuk.web.Member.entity.Member;
import com.together.MunDeuk.web.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    log.info("--------------------------- UserDetailService ---------------------------");

    Member member = memberRepository.selectMember(username);

    return User.withUsername(member.getEmail())
        .password("{noop}"+member.getPassword())
        // todo : redirect issue
        .roles(String.valueOf(member.getMemberAuth()))
        .build();
  }
}
