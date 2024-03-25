package com.together.MunDeuk.web.Member.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailService {

  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    Member findByUserName = memberRepository.verifiedMember(username);
//    // todo : dto 구현 (memberdto에서 구현? or 새로 dto 구현?)
//    MemberDto memberDto = new MemberDto.transform(findByUserName);
//  }

}
