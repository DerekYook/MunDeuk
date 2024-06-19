package com.together.MunDeuk.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler2 extends SimpleUrlAuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    // 로그에 오류 메시지 기록
    logger.error("Authentication failed: " + exception.getMessage());

    // 리디렉션 전에 오류 메시지를 요청에 추가
    request.getSession().setAttribute("error.message", exception.getMessage());

    // 기본 실패 핸들러 호출
    super.onAuthenticationFailure(request, response, exception);
  }
}
