package com.together.MunDeuk.web.Member.service;

import com.together.MunDeuk.web.Member.entity.Member;
import com.together.MunDeuk.web.Member.repository.MemberRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public List<Member> getMemberLists() {
    return memberRepository.selectMembers();
  }

  public Member validMember(String email) {
    Member verifiedMember = memberRepository.verifiedMember(email);
    return verifiedMember;
  }
}
