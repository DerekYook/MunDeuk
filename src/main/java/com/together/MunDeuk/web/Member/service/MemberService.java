package com.together.MunDeuk.web.Member.service;

import com.together.MunDeuk.web.Member.entity.Member;
import com.together.MunDeuk.web.Member.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public List<Member> getMemberLists() {
    return memberRepository.selectMembers();
  }

  public Member loginMember(String email, String password){
    System.out.println(email);
    System.out.println(password);
    return memberRepository.loginMember(email, password);
  }

  public long getMemberIndex() {
    return memberRepository.selectMaxMemberIdx();
  }

  public void signUpMember(long memberId, String nickName, String email, String password) {
    memberRepository.registerMember(memberId, nickName, email, password);
  }

  public void socialSignUp(long memberId, String nickName, String email, String socialId, String socialType){
    memberRepository.saveSocialMember(memberId, nickName, email, socialId, socialType);
  }
}
