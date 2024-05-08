package com.together.MunDeuk.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
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
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    log.info("--------------------------- CommonLoginSuccessHandler ---------------------------");

    OAuth2PrincipalDetail principal = (OAuth2PrincipalDetail) authentication.getPrincipal();

    log.info("authentication.getPrincipal() = {}", principal);

    Map<String, Object> responseMap = principal.getMemberInfo();

    int accessTokenLiveTime = JwtTokenizer2.ACCESS_EXP_TIME;
    int refreshTokenLiveTime = JwtTokenizer2.REFRESH_EXP_TIME;

    responseMap.put("accessToken", JwtTokenizer2.generateToken(responseMap, accessTokenLiveTime));
    responseMap.put("refreshToken", JwtTokenizer2.generateToken(responseMap, refreshTokenLiveTime));

    Gson gson = new Gson();
    String json = gson.toJson(responseMap);

    response.setContentType("application/json; charset=UTF-8");

    PrintWriter writer = response.getWriter();
    writer.println(json);
    writer.flush();
  }
}
