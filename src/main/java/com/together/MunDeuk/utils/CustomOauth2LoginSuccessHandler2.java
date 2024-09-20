package com.together.MunDeuk.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.together.MunDeuk.web.OAuth2.domain.OAuth2PrincipalDetail;
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

@Component(value = "customOAuth2AuthenticationSuccessHandler2")
@Slf4j
@RequiredArgsConstructor
public class CustomOauth2LoginSuccessHandler2 implements AuthenticationSuccessHandler {

  @Value("${jwt.access-header}")
  public String ACCESS_HEADER;

  @Value("${jwt.refresh-header}")
  public String REFRESH_HEADER;

  @Value("${jwt.access-exp-time}")
  public int ACCESS_EXP_TIME;

  @Value("${jwt.refresh-exp-time}")
  public int REFRESH_EXP_TIME;

  private final CookieUtil cookieUtil;
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
  private final JwtTokenizer2 jwtTokenizer2;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    log.info(
        "--------------------------- CommonOauth2LoginSuccessHandler ---------------------------");
    log.info("Principal class: " + authentication.getPrincipal().getClass().getName());

    OAuth2PrincipalDetail principal = (OAuth2PrincipalDetail) authentication.getPrincipal();
    String gb = String.valueOf(principal.getMember().getSocialType()).toLowerCase();
    log.info("authentication.getPrincipal() = {}", principal);

    Map<String, Object> responseMap = principal.getMemberInfo();

    responseMap.put("role", "[ROLE_User]");

//    int accessTokenLiveTime = JwtTokenizer2.ACCESS_EXP_TIME;
//    int refreshTokenLiveTime = JwtTokenizer2.REFRESH_EXP_TIME;

    int accessTokenLiveTime = ACCESS_EXP_TIME;
    int refreshTokenLiveTime = REFRESH_EXP_TIME;

//    // static 분리
////    // Cookie로 넣을 때 (redirect시 token전달이 안됨)
////    ResponseCookie accessTokenCookie = cookieUtil.createCookieByUserId(JwtTokenizer2.ACCESS_HEADER,
////        JwtTokenizer2.generateToken(responseMap, accessTokenLiveTime));
////    ResponseCookie refreshTokenCookie = cookieUtil.createCookieByUserId(
////        JwtTokenizer2.REFRESH_HEADER,
////        JwtTokenizer2.generateToken(responseMap, refreshTokenLiveTime));
    ResponseCookie accessTokenCookie = cookieUtil.createCookieByUserId(ACCESS_HEADER, jwtTokenizer2.generateToken(responseMap, accessTokenLiveTime));
    ResponseCookie refreshTokenCookie = cookieUtil.createCookieByUserId(REFRESH_HEADER, jwtTokenizer2.generateToken(responseMap, refreshTokenLiveTime));

    // Cookie로 넣을 때
    response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
    if (refreshTokenCookie == null) {
      response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
    } else {
      response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
    }

    // Redirect Strategy
    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
      response.setContentType("application/json;charset=UTF-8");
      response.setStatus(HttpServletResponse.SC_OK);

      Map<String, Object> responseData = new HashMap<>();
      String targetUrl =
          determineTargetUrl(authentication) + "?gb=" + gb + "&loginSuccess=true"; // 사용자 역할에 따라 URL 결정
      responseData.put("redirectUrl", targetUrl); // 리디렉션할 URL을 JSON 응답에 포함
      new ObjectMapper().writeValue(response.getWriter(), responseData);
    } else {
      handle(request, response, authentication);
    }
    clearAuthenticationAttributes(request);
  }

  private void handle(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {
    OAuth2PrincipalDetail principal = (OAuth2PrincipalDetail) authentication.getPrincipal();
    String gb = String.valueOf(principal.getMember().getSocialType()).toLowerCase();

    String targetUrl = determineTargetUrl(authentication) + "?gb=" + gb + "&loginSuccess=true";
    log.info("Authentication : " + authentication);

    if (response.isCommitted()) {
      log.info("Response has already been committed. Unable to redirect to " + targetUrl);
      return;
    }

    redirectStrategy.sendRedirect(request, response, targetUrl);
  }

  private String determineTargetUrl(final Authentication authentication) {

    Map<String, String> roleTargetUrlMap = new HashMap<>();
    roleTargetUrlMap.put("User", "/main");

    final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    for (final GrantedAuthority grantedAuthority : authorities) {
      String authorityName = grantedAuthority.getAuthority();
      if(roleTargetUrlMap.containsKey(authorityName)){
        return "/oauthRedirect";
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
