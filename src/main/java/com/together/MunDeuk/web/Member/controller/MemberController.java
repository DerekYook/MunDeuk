package com.together.MunDeuk.web.Member.controller;

import com.together.MunDeuk.web.Member.entity.Member;
import com.together.MunDeuk.web.Member.mapper.MemberMapper;
import com.together.MunDeuk.web.Member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Validated
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @RequestMapping(value = "/main")
    public String test() {
        return "web/common/main";
    }

    @RequestMapping(value = "/members")
    public ResponseEntity getAllMembers(){
        List<Member> memberLists = memberService.getMemberLists();

        return new ResponseEntity(memberMapper.membersToMemberResponseDtos(memberLists), HttpStatus.OK);
    }

    @RequestMapping(value = "/ajax/loginProcess", method = RequestMethod.POST)
    // todo : parameter변경
    public String confirmLogin(@RequestParam String email, @RequestParam String password){
        int accountCnt = memberService.vaildMember(email, password);
        System.out.println("++++++++++");
        System.out.println(email);
        System.out.println(password);
        System.out.println(accountCnt);
        // todo : 성공 / 실패 페이지로 반환
        if ( accountCnt != 1 ) {
            return "N";
        } else {
            return "Y";
        }
    }
}
