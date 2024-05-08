package com.together.MunDeuk.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

//@Component(value = "customAuthenticationSuccessHandler")
//@Slf4j
//@RequiredArgsConstructor
//public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
public class CustomLoginSuccessHandler {
//
//  private final Log logger = LogFactory.getLog(this.getClass());
//
//  private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//  // 성공시 토큰 발행
//  private JwtTokenizer jwtTokenizer;
//
//  public CustomLoginSuccessHandler(JwtTokenizer jwtTokenizer) {
//    this.jwtTokenizer = jwtTokenizer;
//  }
//
//  @Override
//  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//      Authentication authentication) throws IOException {
//
//    // JWT 토큰 생성
//    String username = authentication.getName();
//    String accessToken = jwtTokenizer.generateAccessToken(Map.of("username", username), username,
//        new Date(System.currentTimeMillis() + jwtTokenizer.getAccessTokenExpirationMillis()),
//        jwtTokenizer.getSecretKey());
//
//    // 쿠키에 JWT 추가
//    Cookie accessTokenCookie = new Cookie("access_token", accessToken);
//    accessTokenCookie.setHttpOnly(true);
//    accessTokenCookie.setSecure(true);
//    accessTokenCookie.setPath("/");
//    response.addCookie(accessTokenCookie);
//
//    String refreshToken = jwtTokenizer.generateRefreshToken(username,
//        new Date(System.currentTimeMillis() + jwtTokenizer.getAccessTokenExpirationMillis()),
//        jwtTokenizer.getSecretKey());
//
//    // 쿠키에 JWT 추가
//    Cookie refreshTokenCookie = new Cookie("refresh_token", refreshToken);
//    refreshTokenCookie.setHttpOnly(true);
//    refreshTokenCookie.setSecure(true);
//    refreshTokenCookie.setPath("/");
//    response.addCookie(refreshTokenCookie);
//
////    // JWT 토큰 발행
////    jwtTokenizer.setAccessTokenHeader(accessToken, response);
////    jwtTokenizer.setRefreshTokenHeader(refreshToken, response);
//
//    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
//      response.setContentType("application/json;charset=UTF-8");
//      response.setStatus(HttpServletResponse.SC_OK);
//
//      Map<String, Object> responseData = new HashMap<>();
//      String targetUrl = determineTargetUrl(authentication); // 사용자 역할에 따라 URL 결정
//      responseData.put("redirectUrl", targetUrl); // 리디렉션할 URL을 JSON 응답에 포함
//
//      new ObjectMapper().writeValue(response.getWriter(), responseData);
//    } else {
//      handle(request, response, authentication);
//    }
//    clearAuthenticationAttributes(request);
//    // 관리자, 사용자 별로 redirect페이지 구분 2nd try
////    handle(request, response, authentication);
////    clearAuthenticationAttributes(request);
//    // 관리자, 사용자 별로 redirect페이지 구분 1st try
////    response.setContentType("application/json;charset=UTF-8");
////    response.setStatus(HttpServletResponse.SC_OK);
////
////    Map<String, Object> responseData = new HashMap<>();
////    if (isAdmin(authentication)) {
////      responseData.put("status", "AdminSuccess");
////    } else {
////      responseData.put("status", "MemberSuccess");
////    }
////
////    new ObjectMapper().writeValue(response.getWriter(), responseData);
//  }
//
//  // 관리자 인증 1st try
////  private boolean isAdmin(Authentication authentication) {
////    return authentication.getAuthorities().stream()
////        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
////  }
//
//  private void handle(HttpServletRequest request, HttpServletResponse response,
//      Authentication authentication) throws IOException {
//    String targetUrl = determineTargetUrl(authentication);
//    logger.debug(authentication);
//
//    if (response.isCommitted()) {
//      logger.debug("Response has already been committed. Unable to redirect to "
//          + targetUrl);
//      return;
//    }
//
//    redirectStrategy.sendRedirect(request, response, targetUrl);
//  }
//
//  private String determineTargetUrl(final Authentication authentication) {
//
//    // roleTargetUrlMap -> targetUrlParameterValue
//    Map<String, String> roleTargetUrlMap = new HashMap<>();
//    roleTargetUrlMap.put("ROLE_User", "/main");
//    roleTargetUrlMap.put("ROLE_Admin", "/admin/main");
//
//    final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//    for (final GrantedAuthority grantedAuthority : authorities) {
//      String authorityName = grantedAuthority.getAuthority();
//      if (roleTargetUrlMap.containsKey(authorityName)) {
//        return roleTargetUrlMap.get(authorityName);
//      }
//    }
//
//    throw new IllegalStateException();
//  }
//
//  private void clearAuthenticationAttributes(HttpServletRequest request) {
//    HttpSession session = request.getSession(false);
//    if (session == null) {
//      return;
//    }
//    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//  }
}
