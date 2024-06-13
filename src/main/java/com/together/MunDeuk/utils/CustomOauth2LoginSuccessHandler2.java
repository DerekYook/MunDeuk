package com.together.MunDeuk.utils;

import com.google.gson.Gson;
import com.together.MunDeuk.web.OAuth2.domain.OAuth2PrincipalDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component(value = "customOAuth2AuthenticationSuccessHandler2")
@Slf4j
@RequiredArgsConstructor
public class CustomOauth2LoginSuccessHandler2 implements AuthenticationSuccessHandler {
  @Autowired
  private CookieUtil cookieUtil;
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    log.info("--------------------------- CommonOauth2LoginSuccessHandler ---------------------------");
    log.info("Principal class: " + authentication.getPrincipal().getClass().getName());

    String provider = (String) ((DefaultOAuth2User)authentication.getPrincipal()).getAttributes().get("provider");
    String email = (String) ((DefaultOAuth2User)authentication.getPrincipal()).getAttributes().get("email");
    String oauthId = "";
    String id = authentication.getName();
    log.info("authentication  : "+ ((DefaultOAuth2User)authentication.getPrincipal()).getAttributes() );

    if(provider.equals("kakao")) {
      Map<String, Object> kakao_account = (Map<String, Object>)((DefaultOAuth2User)authentication.getPrincipal()).getAttributes().get("kakao_account");
      email =  (String) kakao_account.get("email") ;
    }

//    OAuth2PrincipalDetail principal = (OAuth2PrincipalDetail) authentication.getPrincipal();
//
//    log.info("authentication.getPrincipal() = {}", principal);
//
//    Map<String, Object> responseMap = principal.getMemberInfo();
//
//    int accessTokenLiveTime = JwtTokenizer2.ACCESS_EXP_TIME;
//    int refreshTokenLiveTime = JwtTokenizer2.REFRESH_EXP_TIME;
//
//    String targetUrl = "/main";
////    // Header로 넣을 때
////    responseMap.put("accessToken", JwtTokenizer2.generateToken(responseMap, accessTokenLiveTime));
////    responseMap.put("refreshToken", JwtTokenizer2.generateToken(responseMap, refreshTokenLiveTime));
//    // Cookie로 넣을 때 (redirect시 token전달이 안됨)
//    ResponseCookie accessTokenCookie = cookieUtil.createCookieByUserId(JwtTokenizer2.ACCESS_HEADER, JwtTokenizer2.generateToken(responseMap, accessTokenLiveTime));
//    ResponseCookie refreshTokenCookie = cookieUtil.createCookieByUserId(JwtTokenizer2.REFRESH_HEADER, JwtTokenizer2.generateToken(responseMap, refreshTokenLiveTime));
//
//    responseMap.put("redirectUrl", targetUrl);
//
//    Gson gson = new Gson();
//    String json = gson.toJson(responseMap);
//
//    response.setContentType("application/json; charset=UTF-8");
//
////    String accessToken = (String) responseMap.get("accessToken");
////    String refreshToken = (String) responseMap.get("refreshToken");
////
////    // Header로 넣을 때
////    JwtTokenizer2.setAccessTokenHeader(accessToken, response);
////    JwtTokenizer2.setRefreshTokenHeader(refreshToken, response);
//    // Cookie로 넣을 때
//    response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
//    if (refreshTokenCookie == null) {
//      response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
//    } else {
//      response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
//    }
//
//    PrintWriter writer = response.getWriter();
//    writer.println(json);
//    writer.flush();
  }
}
