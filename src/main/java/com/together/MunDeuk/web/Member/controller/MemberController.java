package com.together.MunDeuk.web.Member.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.together.MunDeuk.web.Member.entity.Member;
import com.together.MunDeuk.web.Member.entity.Member.MemberAuth;
import com.together.MunDeuk.web.Member.mapper.MemberMapper;
import com.together.MunDeuk.web.Member.service.MemberService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Validated
@Slf4j
public class MemberController {

  private final MemberService memberService;
  private final MemberMapper memberMapper;

  @RequestMapping(value = "/loginFail")
  public String loginFail() {
    return "web/common/loginFail";
  }

  @RequestMapping(value = "/main")
  public String mainPage() {
    return "web/common/main";
  }

  @RequestMapping(value = "/login")
  public String test() {
    return "web/common/login";
  }

  @RequestMapping(value = "/members")
  public ResponseEntity getAllMembers() {
    List<Member> memberLists = memberService.getMemberLists();

    return new ResponseEntity(memberMapper.membersToMemberResponseDtos(memberLists), HttpStatus.OK);
  }

//  // CustomLoginSuccessHandler에서 처리
//  @ResponseBody
//  @RequestMapping(value = "/ajax/loginProcess", method = RequestMethod.POST)
//  // todo : parameter변경
////  public Map<String, Object> confirmLogin(@RequestParam String email, @RequestParam String password) {
//  public Map<String, Object> confirmLogin() {
//    Map<String, Object> result = new HashMap<>();
//    boolean accountChk = false;
//    Member verifiedMember = null;
//
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    if(authentication != null && authentication.isAuthenticated()){
//      String username = authentication.getName();
//      verifiedMember = memberService.validMember(username);
//      accountChk = true;
//    }
//
//    if (!accountChk) {
//      result.put("status", "fail");
//    } else {
//      if (verifiedMember.getMemberAuth() == MemberAuth.Admin){
//        result.put("status", "AdminSuccess");
//      } else {
//        result.put("status", "MemberSuccess");
//      }
//
//    }
//    return result;
//  }

  @ResponseBody
  @RequestMapping(value = "/members/signin", method = RequestMethod.POST)
  public ResponseEntity signIn(@RequestParam String nickName, @RequestParam String email, @RequestParam String password) throws Exception{
    // todo : 휴대폰 본인 인증 혹은 이메일 인증 서비스 추가
//    Map<String, Object> result = new HashMap<>();
//    result.put("nickName",nickName);
//    result.put("email",email);
//    result.put("password",password);

    long id = memberService.getMemberIndex();
    memberService.signInMember(id, nickName,email,password);

    return new ResponseEntity<>(null, HttpStatus.OK);
  }
}
