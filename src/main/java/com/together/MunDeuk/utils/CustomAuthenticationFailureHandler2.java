package com.together.MunDeuk.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.together.MunDeuk.exception.CustomCaptchaException;
import com.together.MunDeuk.exception.CustomOAuth2Exception;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler2 extends SimpleUrlAuthenticationFailureHandler {

  @Value("${jwt.access-header}")
  public String ACCESS_HEADER;

  private final CookieUtil cookieUtil;

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    logger.info("--------------------------- OAuth2LoginFailHandler ---------------------------");

    // 로그에 오류 메시지 기록
    logger.error("Authentication failed: " + exception.getMessage());

    // 리디렉션 전에 오류 메시지를 요청에 추가
    request.getSession().setAttribute("error.message", exception.getMessage());

    String msg = exception.getMessage() == null ? "" : exception.getMessage();

    // 원인(cause)이 CustomOAuth2Exception인지 확인하고 그 메시지를 추출
    if (exception.getCause() instanceof CustomOAuth2Exception) {
      CustomOAuth2Exception customException = (CustomOAuth2Exception) exception.getCause();
      msg = customException.getMessage();

//      CookieUtil cookieUtil = new CookieUtil();

//      // static 분리
//      ResponseCookie accessTokenCookie = cookieUtil.createCookie(JwtTokenizer2.ACCESS_HEADER, customException.getToken());
      ResponseCookie accessTokenCookie = cookieUtil.createCookie(ACCESS_HEADER, customException.getToken());

      response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
    }

//    // 기본 실패 핸들러 호출
//    super.onAuthenticationFailure(request, response, exception);

    if(!msg.equals("")){
      if(msg.equals("INVALID_PROVIDER")){
        logger.error("UNKOWN PROVIDER");
        // todo : redirect 처리
        response.sendRedirect("/error/FAILED_PROVIDER");
      } else if(msg.equals("NOT_FOUND")){
        logger.error("NOT FOUND ACCOUNT");
        response.sendRedirect("/error/NO_ACCOUNT");
      }

    }

  }
}
