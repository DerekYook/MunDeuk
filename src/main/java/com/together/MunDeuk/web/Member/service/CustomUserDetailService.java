package com.together.MunDeuk.web.Member.service;

import com.together.MunDeuk.web.Member.dto.MemberDto;
import com.together.MunDeuk.web.Member.entity.Member;
import com.together.MunDeuk.web.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

  private final MemberRepository memberRepository;
//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
//  }
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member findByUserName = memberRepository.verifiedMember(username);
    // todo : dto 구현 (memberdto에서 구현? or 새로 dto 구현?)
    MemberDto memberDto = new MemberDto.transform(findByUserName);
  }

}
