package com.together.MunDeuk.web.Member.controller;

import com.together.MunDeuk.web.Member.entity.Member;
import com.together.MunDeuk.web.Member.mapper.MemberMapper;
import com.together.MunDeuk.web.Member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    return "/web/common/loginFail";
  }

  @RequestMapping(value = "/main")
  public String mainPage() {
    return "/web/common/main";
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

  @ResponseBody
  @RequestMapping(value = "/ajax/loginProcess", method = RequestMethod.POST)
  // todo : parameter변경
  public Map<String, Object> confirmLogin(@RequestParam String email,
      @RequestParam String password) {
    Map<String, Object> result = new HashMap<>();
    int accountCnt = memberService.vaildMember(email, password);

    if (accountCnt != 1) {
      result.put("status", "fail");
    } else {
      result.put("status", "success");
    }
    return result;
  }
}
