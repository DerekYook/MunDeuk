package com.together.MunDeuk.utils;

import com.google.gson.Gson;
import com.together.MunDeuk.exception.CustomExpiredJwtException;
import com.together.MunDeuk.exception.CustomJwtException;
import com.together.MunDeuk.web.Member.service.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
// JWT 토큰 인증
public class JwtAuthenticationFilter2 extends OncePerRequestFilter {

  @Autowired
  private CustomUserDetailService customUserDetailService;
  JwtTokenizer2 jwtTokenizer = new JwtTokenizer2();

  @Autowired
  private CookieUtil cookieUtil;

  // 필터를 적용하지 않을 API들
  private static final String[] whitelist = {"/favicon.ico", "/signUp", "/login", "/refresh", "/",
      "/ajax/loginProcess"
      , "/WEB-INF/jsp/web/member/signUp.jsp", "/WEB-INF/jsp/web/common/login.jsp"
      , "/js/*"};

//  // 토큰 헤더 검증
//  private static void checkAuthorizationHeader(String header) {
//    if(header == null) {
//      log.info("로그인이 필요합니다.");
//      throw new CustomJwtException("토큰이 전달되지 않았습니다");
//    } else if (!header.startsWith(JwtTokenizer2.BEARER_PREFIX)) {
//      throw new CustomJwtException("BEARER 로 시작하지 않는 올바르지 않은 토큰 형식입니다");
//    }
//  }

  // 필터를 거치지 않을 URL 을 설정하고, true 를 return 하면 현재 필터를 건너뛰고 다음 필터로 이동
  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String requestURI = request.getRequestURI();
    return PatternMatchUtils.simpleMatch(whitelist, requestURI);
  }

//  // Header를 이용한 필터
//  @Override
//  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//    log.info("--------------------------- JwtVerifyFilter ---------------------------");
//
//    String authHeader = request.getHeader(JwtTokenizer2.AUTHORIZATION_HEADER);
//
//    try {
//      checkAuthorizationHeader(authHeader);   // header 가 올바른 형식인지 체크
//      String token = JwtTokenizer2.getTokenFromHeader(authHeader);
//      Authentication authentication = JwtTokenizer2.getAuthentication(token);
//
//      log.info("authentication = {}", authentication);
//
//      SecurityContextHolder.getContext().setAuthentication(authentication);
//
//      filterChain.doFilter(request, response);    // 다음 필터로 이동
//    } catch (Exception e) {
//      Gson gson = new Gson();
//      String json = "";
//      if (e instanceof CustomExpiredJwtException) {
//        json = gson.toJson(Map.of("Token_Expired", e.getMessage()));
//      } else {
//        json = gson.toJson(Map.of("error", e.getMessage()));
//      }
//
//      response.setContentType("application/json; charset=UTF-8");
//      PrintWriter printWriter = response.getWriter();
//      printWriter.println(json);
//      printWriter.close();
//    }
//  }

  // 1nd doFilterInternal
//  @Override
//  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//      FilterChain filterChain) throws ServletException, IOException {
//    log.info("--------------------------- JwtVerifyFilter ---------------------------");
//
//    if(!cookieUtil.chkCookie(request,"Access")) {
//      log.info("로그인이 필요합니다.");
////    String accessToken = cookieUtil.getCookie(request, "Access").getValue();
////    if (accessToken.equals("") || accessToken == null) {
////      log.info("로그인이 필요합니다.");
////      // Exception처리를 하면 토큰이 없을때 진행이 막힘(Oauth초기로그인) => try안쪽에서 처리
////      throw new CustomJwtException("토큰이 전달되지 않았습니다");
//    } else {
//      log.info("---- branch Local Service / Oauth2 Service ----");
//      String accessToken = cookieUtil.getCookie(request, "Access").getValue();
//      try {
//        String username = jwtTokenizer.getUsernameFromToken(accessToken);
//        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
//
//        if (jwtTokenizer.validateToken(accessToken) != null) {
//          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//              userDetails, null, userDetails.getAuthorities());
//          SecurityContextHolder.getContext().setAuthentication(authentication);
//        } else {
//          throw new CustomJwtException("토큰이 유효하지 않습니다");
//        }
////        // try 1
////        // redirect Page적용
////        boolean isHandle = false;
////
////        if (!isAjax(request)) {
////          if ("GET".equalsIgnoreCase(request.getMethod())) {
////            isHandle = true;
////          }
////        }
////
////        String requestUri = request.getRequestURI();
////
////        if (isHandle) {
////          String loginRedirectUrl = requestUri;
////          request.getSession().setAttribute("loginRedirectUrl", loginRedirectUrl);
////        }
////        // jsp Redirect시 빈페이지 방지
////        response.setContentType("text/html;charset=UTF-8");
//
//      } catch (CustomExpiredJwtException e) {
//        log.info("토큰이 만료되었습니다: " + e.getMessage());
//        throw e;
//      } catch (Exception e) {
//        log.error("토큰 검증 중 오류 발생: " + e.getMessage());
////        throw new CustomJwtException("토큰 검증 중 오류가 발생했습니다", e);
//      }
//    }
//    // try 2
//    // 확인해보니 토큰 검증만 하고 filter기능없이 종료되어서 그랬다.....
//    filterChain.doFilter(request,response);
//    logger.info("필터 체인을 실행했습니다.");
//  }
//
////  // redirect Page적용
////  private boolean isAjax(HttpServletRequest request) {
////    String ajaxHeader = request.getHeader("X-Requested-With");
////    return "XMLHttpRequest".equals(ajaxHeader);
////  }

  // 2nd doFilterInternal
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    log.info("--------------------------- JwtVerifyFilter ---------------------------");

    if(!cookieUtil.chkCookie(request,"Access")) {
      log.info("로그인이 필요합니다.");
    } else {
      log.info("---- branch Local Service / Oauth2 Service ----");
      String accessToken = cookieUtil.getCookie(request, "Access").getValue();
      jwtTokenizer.getAuthentication(accessToken);

      try {
        String username = jwtTokenizer.getUsernameFromToken(accessToken);
        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

        if (jwtTokenizer.validateToken(accessToken) != null) {
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
          throw new CustomJwtException("토큰이 유효하지 않습니다");
        }
      } catch (CustomExpiredJwtException e) {
        log.info("토큰이 만료되었습니다: " + e.getMessage());
        throw e;
      } catch (Exception e) {
        log.error("토큰 검증 중 오류 발생: " + e.getMessage());
      }
    }
    filterChain.doFilter(request,response);
    logger.info("필터 체인을 실행했습니다.");
  }

}
