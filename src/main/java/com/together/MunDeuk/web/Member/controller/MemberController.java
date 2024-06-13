package com.together.MunDeuk.web.Member.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.together.MunDeuk.utils.CookieUtil;
import com.together.MunDeuk.utils.JwtTokenizer2;
import com.together.MunDeuk.web.Member.entity.Member;
import com.together.MunDeuk.web.Member.entity.Member.MemberAuth;
import com.together.MunDeuk.web.Member.mapper.MemberMapper;
import com.together.MunDeuk.web.Member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.security.Principal;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@Validated
@Slf4j
public class MemberController {

  private final MemberService memberService;
  private final MemberMapper memberMapper;
  private final CookieUtil cookieUtil;

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
    Map<String, Object> responseMap = new HashMap<>();

//    responseMap.put("name", email);
//    responseMap.put("password", password);

//    String targetUrl = "/main";

    int accessTokenLiveTime = JwtTokenizer2.ACCESS_EXP_TIME;
    int refreshTokenLiveTime = JwtTokenizer2.REFRESH_EXP_TIME;

    ResponseCookie accessTokenCookie = cookieUtil.createCookie(JwtTokenizer2.ACCESS_HEADER, JwtTokenizer2.generateToken(responseMap, accessTokenLiveTime));
    ResponseCookie refreshTokenCookie = cookieUtil.createCookie(JwtTokenizer2.REFRESH_HEADER, JwtTokenizer2.generateToken(responseMap, refreshTokenLiveTime));

    response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
    response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
//     .jsp반영
//    response.setContentType("text/html;charset=UTF-8");
//    response.sendRedirect(targetUrl);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

//  // CustomLoginSuccessHandler에서 처리(여기는 그냥 스루패스)
//  // todo : parameter변경
////  public Map<String, Object> confirmLogin(@RequestParam String email, @RequestParam String password) {
//  public Map<String, Object> confirmLogin() {
//    Map<String, Object> result = new HashMap<>();
//
//    boolean accountChk = false;
//    Member verifiedMember = null;
//
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    if(authentication != null && authentication.isAuthenticated()){
//      String username = authentication.getName();
////      verifiedMember = memberService.validMember(username);
//      accountChk = true;
//    }
//
////    if (!accountChk) {
////      result.put("status", "fail");
////    } else {
////      if (verifiedMember.getMemberAuth() == MemberAuth.Admin){
////        result.put("status", "AdminSuccess");
////      } else {
////        result.put("status", "MemberSuccess");
////      }
////
////    }
//    return result;
//  }

  @ResponseBody
  @RequestMapping(value = "/member/signUp", method = RequestMethod.POST)
  public ResponseEntity signUp(@RequestParam String nickName, @RequestParam String email, @RequestParam String password) throws Exception{
    // todo : 휴대폰 본인 인증 혹은 이메일 인증 서비스 추가
//    Map<String, Object> result = new HashMap<>();
//    result.put("nickName",nickName);
//    result.put("email",email);
//    result.put("password",password);

    long id = memberService.getMemberIndex();
    memberService.signUpMember(id, nickName, email, password);

    return new ResponseEntity<>(null, HttpStatus.OK);
  }

//  @GetMapping("/user")
//  public Mono<Principal> user(Mono<Principal> principal){
//    return principal;
//  }
//
//  @RequestMapping("/oAuth2/loginProcess")
//  public String oAuth2LoginProcess(HttpServletRequest request, HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttributes) throws CException {
//    log.info("/oAuth2/loginProcess");
//    String returnPage = "redirect:/main/authResultPopup.do";
//
//    String oauthId = session.getAttribute("oauthId") != null ? session.getAttribute("oauthId").toString() : "";
//    String provider = session.getAttribute("provider") != null ? session.getAttribute("provider").toString() : "";
//    String email = session.getAttribute("email") != null ? session.getAttribute("email").toString() : "";
//    String loginRedirectUrl = Optional.ofNullable(session.getAttribute("loginRedirectUrl"))
//        .map(o -> o.toString())
//        .orElse("/index");
//
//    if(!StringUtils.hasText(oauthId) || !StringUtils.hasText(email) || !StringUtils.hasText(provider)) {
//      log.error("oAuth IllegalStatementException Error!!!");
//      throw new COAuth2AuthenticationException("NOT_FOUND");
//    }
//
//    OAuthVO oAuthVO = new OAuthVO();
//    oAuthVO.setOauthId(oauthId);
//    oAuthVO.setOauthProvider(provider);
//    oAuthVO.setEmail(email);
//    oAuthVO.setHandleFlag("LOGIN");
//    if(session.getAttribute("userInfo") != null) {
//      oAuthVO.setHandleFlag("LINK");
//    } else if (session.getAttribute("SESSION_MOBILE") != null) {
//      oAuthVO.setHandleFlag("JOIN");
//    }
//
//    try {
//
//      if ("LINK".equals(oAuthVO.getHandleFlag())) {
//        // 간편연동 처리
//        KtUserVO ownUserVO = (KtUserVO) session.getAttribute("userInfo");
//
//        oAuth2Service.oAuthLink(request, oAuthVO, ownUserVO);
//
//      } else {
//
//        Map<String, Object> loginResultMap = oAuth2Service.oAuthLogin(oAuthVO);
//        String errCode = loginResultMap.get("err_code").toString();
//        String errMsg = loginResultMap.get("err_msg") != null ? loginResultMap.get("err_msg").toString() : "";
//        KtUserVO userVO = loginResultMap.get("KtUserVO") != null ? (KtUserVO) loginResultMap.get("KtUserVO") : null;
//
//        if("JOIN".equals(oAuthVO.getHandleFlag())) {
//          // 회원가입
//          if("-99999".equals(errCode)) {
//            session.setAttribute("SESSION_EMAIL", email);
//            session.setAttribute("SESSION_PROVIDER", provider);
//            session.setAttribute("SESSION_OAUTH_ID", oauthId);
//          }
//        } else {
//          // 로그인
//          if("-99999".equals(errCode)) {
//            // 회원가입 리다이렉트 - authPopup에서 분기처리
//
//          } else if("0".equals(errCode)) {
//            // 홈페이지 로그인
//            setEnableAccessUser(userVO, response);
//
//            // 로그인 로그
//            historyService.insertRequestHistory(request, userVO, "requestMemberLoginProcess");
//
//            // 세션 EMAIL 정보 초기화
//            session.invalidate();
//
//            String passChangeYn = loginResultMap.get("pass_change_yn") != null ? loginResultMap.get("pass_change_yn").toString() : "N";
//            String passDateChangeYn = loginResultMap.get("pass_date_change_yn") != null ? loginResultMap.get("pass_date_change_yn").toString() : "N";
//
//            if (!StringUtils.hasText(userVO.getOauthProvider()) || "pass".equals(userVO.getOauthProvider())) {
//              redirectAttributes.addFlashAttribute("passChangeYn", passChangeYn);
//
//              redirectAttributes.addFlashAttribute("passDateChangeYn", passDateChangeYn);
//              redirectAttributes.addFlashAttribute("ownId", userVO.getUserId());
//
//            } else {
//              *
//               * 간편가입
//               * 비밀번호변경 기능 비활성화로 passChangeYn 불필요
//               * 90일주기 비밀번호 변경은 솔루션에서 별도로 예외처리 되어있지 않아 홈페이지에서 N으로 처리
//
//              redirectAttributes.addFlashAttribute("passDateChangeYn", "N");
//            }
//
//            redirectAttributes.addFlashAttribute("loginRedirectUrl", loginRedirectUrl);
//
//          } else {
//            if("Y".equalsIgnoreCase(loginResultMap.get("dormancy_yn").toString())) {
//              KtUserDormancyDTO dormancyDTO = new KtUserDormancyDTO();
//              dormancyDTO.setUserId(userVO.getUserId());
//              dormancyDTO.setMobileNumber(loginResultMap.get("cellphone").toString());
//              redirectAttributes.addFlashAttribute("dormancyInfo", dormancyDTO);
//
//            } else {
//              if(errMsg.contains("5회이상") || errMsg.contains("실패로")) {
//                redirectAttributes.addFlashAttribute("loginException", getMessage("exeedloginCount.msg2"));
//              } else if(errMsg.contains("탈회")) {
//                redirectAttributes.addFlashAttribute("loginException", getMessage("withdrawal.msg"));
//              } else {
//                redirectAttributes.addFlashAttribute("loginException", getMessage("loginFailed.msg"));
//              }
//            }
//          }
//        }
//
//        returnPage = "redirect:/main/authResultPopup.do";
//      }
//
//    } catch (COAuth2AuthenticationException e) {
//      log.info("COAuth2AuthenticationException error");
//      if("INVALID_PROVIDER".equals(e.getMessage())) {
//        log.info("INVALID_PROVIDER error");
//        throw new COAuth2AuthenticationException("INVALID_PROVIDER");
//      } else if("DUPLICATE_LINK".equals(e.getMessage())) {
//        log.info("DUPLICATE_LINK error");
//        throw new COAuth2AuthenticationException("DUPLICATE_LINK");
//      }
//    } catch (Throwable e) {
//      log.error("/oAuth2/loginProcess -ExceptionError!");
//    }
//
//    return returnPage;
//  }
//
//
////  @RequestMapping(value = {"/oAuth2/loginProcessByNaver", "/oAuth2/loginProcessByGoogle", "/oAuth2/loginProcessByKakao", "/oAuth2/appleLoginCallback"})
////  @ResponseBody
////  public ResponseEntity<?> loginProcessByOAuth2(HttpServletRequest request, HttpServletResponse response, HttpSession session,
////      @RequestBody HashMap<String, String> hMap) throws CException {
////
////    log.info("loginProcessByOAuth2");
////
////    CommonResult result = new CommonResult();
////    HashMap<String, Object> rsMap = new HashMap<>();
////    log.info("hMap ==== : " + hMap);
////
////    String oauthId = "";
////    String provider = "";
////    String email = "";
////    String accessToken = "";
////    String loginRedirectUrl = Optional.ofNullable(session.getAttribute("loginRedirectUrl"))
////        .map(o -> o.toString())
////        .orElse("/index");
////
////    int resultCode = 0;
////    String resultMsg = "";
////    String resultParm = "";
////
////    try {
////
////      if(request.getRequestURI().contains("loginProcessByNaver") || request.getRequestURI().contains("loginProcessByGoogle")) {
////        // 네이버 / 구글
////        oauthId = hMap.get("id") == null ? "" : hMap.get("id");
////        provider = "naver";
////        email = hMap.get("email") == null ? "" : hMap.get("email");
////        accessToken = hMap.get("access_token") == null ? "" : hMap.get("access_token");
////        if(request.getRequestURI().contains("loginProcessByGoogle")) {
////          provider = "google";
////        }
////      } else if (request.getRequestURI().contains("loginProcessByKakao")) {
////        // 카카오
////        oauthId = hMap.get("id") == null ? "" : hMap.get("id");
////        provider = "kakao";
////        email = hMap.get("email") == null ? "" : hMap.get("email");
////        accessToken = hMap.get("access_token") == null ? "" : hMap.get("access_token");
////      } else {
////        // 애플
////        String token = MapUtils.getString(hMap, "idToken");
////        log.info("######## token ##########" + token);
////        String[] check = token.split("\\.");
////
////        log.info("######################################################## g : " + check.length);
////
////        Base64.Decoder decoder = Base64.getDecoder();
////        String payload = new String(decoder.decode(check[1]));
////
////        for (int i = 0; i < check.length - 1; i++) {
////          log.info("########################################################");
////          log.info("NEW APPLE USER : " + check[i]);
////          log.info("NEW APPLE USER : " + new String(decoder.decode(check[i])));
////          log.info("########################################################");
////        }
////
////        log.info("######## payload ##########" + payload);
////        ObjectMapper mapper = new ObjectMapper();
////        Map<String, Object> tokenReturnMap = mapper.readValue(payload, Map.class);
////
////        log.info("######## tokenReturnMap ##########" + tokenReturnMap);
////
////        oauthId = tokenReturnMap.get("sub") == null ? "" : tokenReturnMap.get("sub").toString();
////        provider = "apple";
////        email = tokenReturnMap.get("email") == null ? tokenReturnMap.get("sub").toString() : tokenReturnMap.get("email").toString();
////        accessToken = "";
////      }
////
////      if(!StringUtils.hasText(oauthId) || !StringUtils.hasText(email) || !StringUtils.hasText(provider)) {
////        log.error("oAuth IllegalStatementException Error!!!");
////        throw new COAuth2AuthenticationException("NOT_FOUND");
////      }
////
////      log.info("{} START!", provider);
////      OAuthVO oAuthVO = new OAuthVO();
////      oAuthVO.setOauthId(oauthId);
////      oAuthVO.setOauthProvider(provider);
////      oAuthVO.setEmail(email);
////      oAuthVO.setHandleFlag("LOGIN");
////      if(session.getAttribute("userInfo") != null) {
////        oAuthVO.setHandleFlag("LINK");
////      } else if (session.getAttribute("SESSION_MOBILE") != null) {
////        oAuthVO.setHandleFlag("JOIN");
////      }
////
////      if ("LINK".equals(oAuthVO.getHandleFlag())) {
////        // 간편연동 처리
////        KtUserVO ownUserVO = (KtUserVO) session.getAttribute("userInfo");
////
////        Map<String, String> linkResultMap = oAuth2Service.oAuthLink(request, oAuthVO, ownUserVO);
////
////        resultCode = Integer.valueOf(linkResultMap.get("resultCode"));
////        resultMsg = linkResultMap.get("resultMsg");
////        resultParm = linkResultMap.get("resultParm");
////
////      } else {
////
////        Map<String, Object> loginResultMap = oAuth2Service.oAuthLogin(oAuthVO);
////        String errCode = loginResultMap.get("err_code").toString();
////        String errMsg = loginResultMap.get("err_msg") != null ? loginResultMap.get("err_msg").toString() : "";
////        KtUserVO userVO = loginResultMap.get("KtUserVO") != null ? (KtUserVO) loginResultMap.get("KtUserVO") : null;
////
////        if("JOIN".equals(oAuthVO.getHandleFlag())) {
////          // 회원가입
////          if("-99999".equals(errCode)) {
////            session.setAttribute("SESSION_EMAIL", email);
////            session.setAttribute("SESSION_PROVIDER", provider);
////            session.setAttribute("SESSION_OAUTH_ID", oauthId);
////
////            resultCode = OAuth2Result.ACCOUNT_JOIN.resultCode;
////            resultMsg = OAuth2Result.ACCOUNT_JOIN.getResultMsg();
////            resultParm = OAuth2Result.ACCOUNT_JOIN.resultParm;
////            rsMap.put("url", OAuth2Result.ACCOUNT_JOIN.resultParm);
////          }
////        } else {
////          // 로그인
////          if("-99999".equals(errCode)) {
////            // 회원가입 리다이렉트
////            resultCode = OAuth2Result.ACCOUNT_NOTFOUND.resultCode;
////            resultMsg = OAuth2Result.ACCOUNT_NOTFOUND.getResultMsg();
////            resultParm = OAuth2Result.ACCOUNT_NOTFOUND.resultParm;
////            rsMap.put("url", OAuth2Result.ACCOUNT_NOTFOUND.resultParm);
////
////          } else if("0".equals(errCode)) {
////            // 홈페이지 로그인
////            setEnableAccessUser(userVO, response);
////
////            // 로그인 로그
////            historyService.insertRequestHistory(request, userVO, "requestMemberLoginProcess");
////
////            //session.invalidate();
////
////            String passChangeYn = loginResultMap.get("pass_change_yn") != null ? loginResultMap.get("pass_change_yn").toString() : "N";
////            String passDateChangeYn = loginResultMap.get("pass_date_change_yn") != null ? loginResultMap.get("pass_date_change_yn").toString() : "N";
////
////            resultCode = OAuth2Result.ACCOUNT_LOGIN.resultCode;
////            resultMsg = OAuth2Result.ACCOUNT_LOGIN.getResultMsg();
////            resultParm = loginRedirectUrl;
////
////            if (!StringUtils.hasText(userVO.getOauthProvider()) || "pass".equals(userVO.getOauthProvider())) {
////              // 자사계정 or PASS
////              if ("Y".equals(passChangeYn)) {
////                // 1순위 비밀번호 변경
////                resultCode = OAuth2Result.PASS_CHANGE.resultCode;
////                resultParm = OAuth2Result.PASS_CHANGE.resultParm;
////              } else if ("Y".equals(passDateChangeYn)) {
////                // 2순위 비밀번호 90일주기 변경
////                resultCode = OAuth2Result.PASS_DATE_CHANGE.resultCode;
////                rsMap.put("ownId", userVO.getUserId());
////              }
////
////            } else {
////              // 간편가입
////            }
////
////            rsMap.put("url", loginRedirectUrl);
////
////          } else {
////            if("Y".equalsIgnoreCase(loginResultMap.get("dormancy_yn").toString())) {
////              // 휴면계정
////              KtUserDormancyDTO dormancyDTO = new KtUserDormancyDTO();
////              dormancyDTO.setUserId(userVO.getUserId());
////              dormancyDTO.setMobileNumber(loginResultMap.get("cellphone").toString());
////
////              resultCode = OAuth2Result.EXCEPT_DORMANCY.resultCode;
////              resultParm = OAuth2Result.EXCEPT_DORMANCY.resultParm;
////              rsMap.put("dormancyInfo", dormancyDTO);
////
////            } else {
////              // 로그인에러
////              if(errMsg.contains("5회이상") || errMsg.contains("실패로")) {
////                resultCode = OAuth2Result.EXCEPT_EXCEED_COUNT.resultCode;
////                resultMsg = OAuth2Result.EXCEPT_EXCEED_COUNT.getResultMsg();
////              } else if(errMsg.contains("탈회")) {
////                resultCode = OAuth2Result.EXCEPT_WITHDRAWAL.resultCode;
////                resultMsg = OAuth2Result.EXCEPT_WITHDRAWAL.getResultMsg();
////              } else {
////                resultCode = OAuth2Result.EXCEPT_LOGIN.resultCode;
////                resultMsg = OAuth2Result.EXCEPT_LOGIN.getResultMsg();
////              }
////            }
////          }
////        }
////      }
////
////    } catch (COAuth2AuthenticationException e) {
////      log.info("COAuth2AuthenticationException error");
////      if("INVALID_PROVIDER".equals(e.getMessage())) {
////        log.info("INVALID_PROVIDER error");
////        throw new COAuth2AuthenticationException("INVALID_PROVIDER");
////      } else if("DUPLICATE_LINK".equals(e.getMessage())) {
////        log.info("DUPLICATE_LINK error");
////        throw new COAuth2AuthenticationException("DUPLICATE_LINK");
////      }
////
////    } catch (Throwable e) {
////      log.info("oAuth2LoginProcess error");
////    }
////
////    String csrf = getCsrfToken(request);
////    result.setNewToken(csrf);
////    result.setResultCode(resultCode);
////    result.setResultMsg(resultMsg);
////    result.setResultParm(resultParm);
////    result.setResultMap(rsMap);
////
////    log.info("rs === : " + result);
////    return ResponseEntity.ok(result);
////  }

  @RequestMapping(value = "/login/oauth2/code/{provider}")
  public String oauth2Login(@PathVariable String provider, HttpRequest request, HttpResponse response) {
    String returnPage = "/main";

    System.out.println("====oauth2 response====");
    System.out.println(provider);
    System.out.println(request);
    System.out.println(response);

    return returnPage;
  }

}
