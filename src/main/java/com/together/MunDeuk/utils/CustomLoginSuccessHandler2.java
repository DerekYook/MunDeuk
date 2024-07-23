package com.together.MunDeuk.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component(value = "customAuthenticationSuccessHandler2")
@Slf4j
@RequiredArgsConstructor
public class CustomLoginSuccessHandler2 implements AuthenticationSuccessHandler {

  @Value("${jwt.access-header}")
  public String ACCESS_HEADER;

  @Value("${jwt.refresh-header}")
  public String REFRESH_HEADER;

  @Value("${jwt.access-exp-time}")
  public int ACCESS_EXP_TIME;

  @Value("${jwt.refresh-exp-time}")
  public int REFRESH_EXP_TIME;

  private final CookieUtil cookieUtil;
  private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  private final JwtTokenizer2 jwtTokenizer2;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    log.info("--------------------------- CommonLoginSuccessHandler ---------------------------");

    // 가입자 검증
    String principal = (String) authentication.getPrincipal();
    String credential = (String) authentication.getCredentials();
    String role = authentication.getAuthorities().toString();

    log.info("authentication = {}", authentication);
    log.info("authentication.getPrincipal() = {}", principal);
    log.info("authentication.getCredentials() = {}", credential);

    Map<String, Object> responseMap = new HashMap<>();

    responseMap.put("email", principal);
    responseMap.put("name", null);
    responseMap.put("role", role);
    responseMap.put("provider", null);
//    responseMap.put("password", credential);

//    // static 풀기
//    int accessTokenLiveTime = JwtTokenizer2.ACCESS_EXP_TIME;
//    int refreshTokenLiveTime = JwtTokenizer2.REFRESH_EXP_TIME;

    int accessTokenLiveTime = ACCESS_EXP_TIME;
    int refreshTokenLiveTime = REFRESH_EXP_TIME;

//    // static 분리
////    // Cookie로 넣을 때 (redirect시 header를 사용하면 token전달이 안되서 cookie로 진행)
////    ResponseCookie accessTokenCookie = cookieUtil.createCookie(JwtTokenizer2.ACCESS_HEADER,
////        JwtTokenizer2.generateToken(responseMap, accessTokenLiveTime));
////    ResponseCookie refreshTokenCookie = cookieUtil.createCookie(JwtTokenizer2.REFRESH_HEADER,
////        JwtTokenizer2.generateToken(responseMap, refreshTokenLiveTime));
    ResponseCookie accessTokenCookie = cookieUtil.createCookie(ACCESS_HEADER,
        jwtTokenizer2.generateToken(responseMap, accessTokenLiveTime));
    ResponseCookie refreshTokenCookie = cookieUtil.createCookie(REFRESH_HEADER,
        jwtTokenizer2.generateToken(responseMap, refreshTokenLiveTime));

    // Cookie로 넣을 때
    response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
    response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

    // Redirect Strategy
    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
      response.setContentType("application/json;charset=UTF-8");
      response.setStatus(HttpServletResponse.SC_OK);

      Map<String, Object> responseData = new HashMap<>();
      String targetUrl = determineTargetUrl(authentication); // 사용자 역할에 따라 URL 결정
      responseData.put("redirectUrl", targetUrl); // 리디렉션할 URL을 JSON 응답에 포함
      new ObjectMapper().writeValue(response.getWriter(), responseData);
    } else {
      handle(request, response, authentication);
    }
    clearAuthenticationAttributes(request);
  }

  private void handle(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {
    String targetUrl = determineTargetUrl(authentication);
    log.info("Authentication : " + authentication);

    if (response.isCommitted()) {
      log.info("Response has already been committed. Unable to redirect to "
          + targetUrl);
      return;
    }

    redirectStrategy.sendRedirect(request, response, targetUrl);
  }

  private String determineTargetUrl(final Authentication authentication) {

    Map<String, String> roleTargetUrlMap = new HashMap<>();
    // todo : redirect issue
    // grantedAuth에 []가 붙어야 하는데 JwtAuthenticationProvider에서 사용자 인증하면서 roles에 [[]]이런식으로 배열속 배열이 되어버려 그렇다. 수정이 필요할까?
    roleTargetUrlMap.put("[ROLE_User]", "/main");
    roleTargetUrlMap.put("[ROLE_Admin]", "/admin/main");

    final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    for (final GrantedAuthority grantedAuthority : authorities) {
      String authorityName = grantedAuthority.getAuthority();
      if (roleTargetUrlMap.containsKey(authorityName)) {
        return roleTargetUrlMap.get(authorityName);
      }
    }
    throw new IllegalStateException();
  }

  private void clearAuthenticationAttributes(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return;
    }
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  }
}
