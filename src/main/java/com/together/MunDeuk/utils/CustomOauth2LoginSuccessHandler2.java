package com.together.MunDeuk.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.together.MunDeuk.web.OAuth2.domain.OAuth2PrincipalDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component(value = "customOAuth2AuthenticationSuccessHandler2")
@Slf4j
@RequiredArgsConstructor
public class CustomOauth2LoginSuccessHandler2 implements AuthenticationSuccessHandler {
  @Autowired
  private CookieUtil cookieUtil;

  private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    log.info("--------------------------- CommonOauth2LoginSuccessHandler ---------------------------");
    log.info("Principal class: " + authentication.getPrincipal().getClass().getName());

//    String clientRegistrationId = (String) ((DefaultOAuth2User)authentication.getPrincipal()).getAttributes().get("provider");
//    String email = (String) ((DefaultOAuth2User)authentication.getPrincipal()).getAttributes().get("email");
//    String oauthId = "";
//    String id = authentication.getName();
//    log.info("authentication  : "+ ((DefaultOAuth2User)authentication.getPrincipal()).getAttributes() );
//
//    if(provider.equals("kakao")) {
//      Map<String, Object> kakao_account = (Map<String, Object>)((DefaultOAuth2User)authentication.getPrincipal()).getAttributes().get("kakao_account");
//      email =  (String) kakao_account.get("email") ;
//    }

    OAuth2PrincipalDetail principal = (OAuth2PrincipalDetail) authentication.getPrincipal();

    log.info("authentication.getPrincipal() = {}", principal);

    Map<String, Object> responseMap = principal.getMemberInfo();

    responseMap.put("role","[ROLE_User]");

    int accessTokenLiveTime = JwtTokenizer2.ACCESS_EXP_TIME;
    int refreshTokenLiveTime = JwtTokenizer2.REFRESH_EXP_TIME;

//    // Header로 넣을 때
//    responseMap.put("accessToken", JwtTokenizer2.generateToken(responseMap, accessTokenLiveTime));
//    responseMap.put("refreshToken", JwtTokenizer2.generateToken(responseMap, refreshTokenLiveTime));
    // Cookie로 넣을 때 (redirect시 token전달이 안됨)
    ResponseCookie accessTokenCookie = cookieUtil.createCookieByUserId(JwtTokenizer2.ACCESS_HEADER, JwtTokenizer2.generateToken(responseMap, accessTokenLiveTime));
    ResponseCookie refreshTokenCookie = cookieUtil.createCookieByUserId(JwtTokenizer2.REFRESH_HEADER, JwtTokenizer2.generateToken(responseMap, refreshTokenLiveTime));

//    String accessToken = (String) responseMap.get("accessToken");
//    String refreshToken = (String) responseMap.get("refreshToken");
//
//    // Header로 넣을 때
//    JwtTokenizer2.setAccessTokenHeader(accessToken, response);
//    JwtTokenizer2.setRefreshTokenHeader(refreshToken, response);
    // Cookie로 넣을 때
    response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
    if (refreshTokenCookie == null) {
      response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
    } else {
      response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
    }

//    String targetUrl = "/main";
//    responseMap.put("redirectUrl", targetUrl);

//    // JSON 응답 보기
//    Gson gson = new Gson();
//    String json = gson.toJson(responseMap);
//
//    response.setContentType("application/json; charset=UTF-8");
//
//    PrintWriter writer = response.getWriter();
//    writer.println(json);
//    writer.flush();

    // todo : 성공 후 팝업 닫고 login페이지 redirect하게
    // Redirect Strategy
    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
      response.setContentType("application/json;charset=UTF-8");
      response.setStatus(HttpServletResponse.SC_OK);

      Map<String, Object> responseData = new HashMap<>();
      String targetUrl = determineTargetUrl(authentication) + "?loginSuccess=true"; // 사용자 역할에 따라 URL 결정
      responseData.put("redirectUrl", targetUrl); // 리디렉션할 URL을 JSON 응답에 포함
      new ObjectMapper().writeValue(response.getWriter(), responseData);
    } else {
      handle(request, response, authentication);
    }
    clearAuthenticationAttributes(request);
  }

  private void handle(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {
    String targetUrl = determineTargetUrl(authentication) + "?loginSuccess=true";
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
    roleTargetUrlMap.put("[ROLE_User]", "/main");

    final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    for (final GrantedAuthority grantedAuthority : authorities) {
      String authorityName = grantedAuthority.getAuthority();
//      if (roleTargetUrlMap.containsKey(authorityName)) {
//        return roleTargetUrlMap.get(authorityName);
//      }
      return "/oauthRedirect";
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
