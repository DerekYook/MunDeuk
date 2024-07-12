package com.together.MunDeuk.utils;

import com.together.MunDeuk.exception.CustomExpiredJwtException;
import com.together.MunDeuk.exception.CustomJwtException;
import com.together.MunDeuk.web.Member.service.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
// JWT 토큰 인증
public class JwtAuthenticationFilter2 extends OncePerRequestFilter {

  @Autowired
  private CustomUserDetailService customUserDetailService;
  JwtTokenizer2 jwtTokenizer;

  CookieUtil cookieUtil;

  // 필터를 적용하지 않을 API들
  private static final String[] whitelist = {"/favicon.ico", "/signUp", "/login", "/refresh",
      "/oauthRedirect", "/",
      "/ajax/loginProcess"
      , "/WEB-INF/jsp/web/member/signUp.jsp", "/WEB-INF/jsp/web/common/login.jsp",
      "/WEB-INF/jsp/web/common/oauthRedirect.jsp"
      , "/js/*"};

  // 필터를 거치지 않을 URL 을 설정하고, true 를 return 하면 현재 필터를 건너뛰고 다음 필터로 이동
  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String requestURI = request.getRequestURI();
    return PatternMatchUtils.simpleMatch(whitelist, requestURI);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    log.info("--------------------------- JwtVerifyFilter ---------------------------");
    log.info(" before provider :", request.authenticate(response));
    if (cookieUtil == null || !cookieUtil.chkCookie(request, "Access")) {
      log.info("로그인이 필요합니다.");
    } else {
      log.info("---- branch Local Service / Oauth2 Service ----");
      String accessToken = cookieUtil.getCookie(request, "Access").getValue();

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
    filterChain.doFilter(request, response);
  }

}
