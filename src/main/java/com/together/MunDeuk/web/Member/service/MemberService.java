package com.together.MunDeuk.web.Member.service;

import com.together.MunDeuk.web.Board.repository.BoardRepository;
import com.together.MunDeuk.web.Member.entity.Member;
import com.together.MunDeuk.web.Member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public List<Member> getMemberLists(){
        return memberRepository.selectMembers();
    }

    public Integer vaildMember(String email, String password){
        int count = memberRepository.countMember(email, password);
        return count;
    }
}
