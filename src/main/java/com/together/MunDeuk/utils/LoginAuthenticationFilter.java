package com.together.MunDeuk.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  public LoginAuthenticationFilter(final String defaultFilterProcessesUrl,
      final AuthenticationManager authenticationManager) {
    super(defaultFilterProcessesUrl, authenticationManager);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

    String method = request.getMethod();

    if (!method.equals("POST")) {
      throw new AuthenticationServiceException(
          "Authentication method not supported: " + request.getMethod());
    }

    ServletInputStream inputStream = request.getInputStream();

    LoginRequestDto loginRequestDto = new ObjectMapper().readValue(inputStream,
        LoginRequestDto.class);
    // AuthenticationManager : ProviderManager의 인스턴스 -> Provider 순회 인증시도
    return this.getAuthenticationManager().authenticate(new JwtAuthenticationToken(
        loginRequestDto.username,
        loginRequestDto.password
    ));
  }

  // json에서 email로 오는 값을 username으로 처리
  public record LoginRequestDto(@JsonProperty("email") String username, String password) {

  }
}
