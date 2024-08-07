package com.together.MunDeuk.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.together.MunDeuk.exception.CustomCaptchaException;
import com.together.MunDeuk.exception.CustomOAuth2Exception;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomLoginFailHandler2 implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    log.info("--------------------------- WebLoginFailHandler ---------------------------");

    request.getSession().setAttribute("error.message", exception.getMessage());

    String msg = exception.getMessage() == null ? "" : exception.getMessage();

    // 원인(cause)이 CustomOAuth2Exception인지 확인하고 그 메시지를 추출
    if (exception instanceof CustomCaptchaException) {
      // 에러 메시지 추출
//      CustomCaptchaException customCaptchaException = (CustomCaptchaException) exception.getCause();
//      msg = customCaptchaException.getMessage();
      msg = exception.getMessage();
    }

    if (!msg.equals("")) {
      if (msg.equals("INVALID_CAPTCHA")) {
        log.error("FAIL CAPTCHA VALIDATE");
//        response.sendRedirect("/error/WRONG_LOGIN");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        Map<String, String> data = new HashMap<>();
        data.put("error", msg);
        response.getWriter().write(new ObjectMapper().writeValueAsString(data));
      } else if (msg.equals("INVALID_ACCOUNT")) {
        log.error("WRONG ACCOUNT INFO");
//        response.sendRedirect("/error/WRONG_LOGIN");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        Map<String, String> data = new HashMap<>();
        data.put("error", msg);
        response.getWriter().write(new ObjectMapper().writeValueAsString(data));
      }
    }
  }
}
