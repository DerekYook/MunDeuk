package com.together.MunDeuk.web.Member.controller;

import com.together.MunDeuk.utils.CookieUtil;
import com.together.MunDeuk.utils.JwtTokenizer2;
import com.together.MunDeuk.web.Member.entity.Member;
import com.together.MunDeuk.web.Member.mapper.MemberMapper;
import com.together.MunDeuk.web.Member.service.MemberService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
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
  private final CookieUtil cookieUtil;
  private final JwtTokenizer2 jwtTokenizer2;

  @Value("${jwt.access-header}")
  public String ACCESS_HEADER;

  @RequestMapping(value = "/loginFail")
  public String loginFail() {
    return "web/common/loginFail";
  }

  @RequestMapping(value = "/main")
  public String mainPage() {
    return "web/common/main";
  }

  @RequestMapping(value = "/login")
  public String login() {
    return "web/common/login";
  }

  @RequestMapping(value = "/signUp")
  public String register() {
    return "web/member/signUp";
  }

  @RequestMapping(value = "/members")
  public ResponseEntity getAllMembers() {
    List<Member> memberLists = memberService.getMemberLists();
    return new ResponseEntity(memberMapper.membersToMemberResponseDtos(memberLists), HttpStatus.OK);
  }

  // form로그인 동작
  @ResponseBody
  @RequestMapping(value = "/ajax/loginProcess", method = RequestMethod.POST)
//  public void confirmLogin(){}
  public ResponseEntity<?> confirmLogin(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
//    Map<String, Object> responseMap = new HashMap<>();
//
//    int accessTokenLiveTime = JwtTokenizer2.ACCESS_EXP_TIME;
//    int refreshTokenLiveTime = JwtTokenizer2.REFRESH_EXP_TIME;
//
//    ResponseCookie accessTokenCookie = cookieUtil.createCookie(JwtTokenizer2.ACCESS_HEADER,
//        JwtTokenizer2.generateToken(responseMap, accessTokenLiveTime));
//    ResponseCookie refreshTokenCookie = cookieUtil.createCookie(JwtTokenizer2.REFRESH_HEADER,
//        JwtTokenizer2.generateToken(responseMap, refreshTokenLiveTime));
//
//    response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
//    response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @ResponseBody
  @RequestMapping(value = "/member/signUp", method = RequestMethod.POST)
  public ResponseEntity signUp(@RequestParam String nickName, @RequestParam String email,
      @RequestParam String password) throws Exception {
    // todo : 휴대폰 본인 인증 혹은 이메일 인증 서비스 추가

    long memberId = memberService.getMemberIndex();
    memberService.signUpMember(memberId, nickName, email, password);

    return new ResponseEntity<>(null, HttpStatus.OK);
  }

  @RequestMapping(value = "/oAuth2SignUp")//, method = RequestMethod.POST)
  public String oAuth2SignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {

    String returnPage = "web/member/oAuth2SignUpSuccess";

    String tempToken = cookieUtil.getCookie(request,"Access").getValue();
    Claims claims = jwtTokenizer2.validateToken(tempToken);

    long memberId = memberService.getMemberIndex();
    String name = claims.get("name").toString();
    String email = claims.get("email").toString();
    String socialId = claims.get("socialId").toString();
    String socialType = claims.get("socialType").toString();

    log.info("oauthInfo = " + name + " / " + email + " / " + socialId + " / " + socialType);

    memberService.socialSignUp(memberId, name, email, socialId, socialType);
//    return new ResponseEntity<>(null, HttpStatus.OK);
    return returnPage;
  }

  @RequestMapping(value = "/oauthRedirect")
  public String oauth2LoginProcess() {
    String returnPage = "web/common/oauthRedirect";
    return returnPage;
  }

  @ResponseBody
  @RequestMapping(value = "/ajax/accountChk", method = RequestMethod.POST)
  public ResponseEntity<?> oauthAccountChk(HttpServletRequest request, HttpServletResponse response) throws IOException {
    log.info("----------------------------------------------");
    log.info("account Check : " + response);
    log.info("account Check : " + request);

    return new ResponseEntity<>(null, HttpStatus.OK);
  }

  @RequestMapping(value = "/error/{errorCode}")
  public String errorRedirect(@PathVariable String errorCode, HttpServletRequest request, HttpServletResponse response){

    String tempToken = cookieUtil.getCookie(request,"Access").getValue();

//    // static 분리
//    ResponseCookie accessTokenCookie = cookieUtil.createCookie(JwtTokenizer2.ACCESS_HEADER, tempToken);
    ResponseCookie accessTokenCookie = cookieUtil.createCookie(ACCESS_HEADER, tempToken);

    response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());

    String returnPage = "web/error/" + errorCode;
    return returnPage;
  }

}
