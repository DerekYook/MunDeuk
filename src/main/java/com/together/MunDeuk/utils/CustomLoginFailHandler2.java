package com.together.MunDeuk.utils;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component(value = "customAuthenticationSuccessHandler")
@Slf4j
@RequiredArgsConstructor
public class CustomLoginFailHandler2 implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    log.info("--------------------------- CommonLoginFailHandler ---------------------------");

    Gson gson = new Gson();
    String json = gson.toJson(Map.of("error", "Login Fail"));

    response.setContentType("application/json; charset=UTF-8");

    PrintWriter printWriter = response.getWriter();
    printWriter.println(json);
    printWriter.close();
  }
}
