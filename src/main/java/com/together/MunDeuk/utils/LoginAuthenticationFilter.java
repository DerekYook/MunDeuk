package com.together.MunDeuk.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.Constants;
import com.together.MunDeuk.exception.CustomCaptchaException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  private final CaptchaUtil captchaUtil;

  public LoginAuthenticationFilter(final String defaultFilterProcessesUrl,
      final AuthenticationManager authenticationManager, CaptchaUtil captchaUtil) {
    super(defaultFilterProcessesUrl, authenticationManager);
    this.captchaUtil = captchaUtil;
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

    // captcha 검증
//    String captchaAnswer = request.getParameter("captchaAnswer");
    String captchaAnswer = loginRequestDto.captchaAnswer;
    String captchaId = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

    System.out.println("++++++++++++++++++");
    System.out.println(captchaAnswer);
    System.out.println(captchaId);

    if(!captchaUtil.validateCaptcha(captchaId, captchaAnswer)){
      logger.info("Captcha Validate Fail : Provided Text : " + captchaId + " / User Text : " + captchaAnswer);
      throw new CustomCaptchaException("INVALID_CAPTCHA");
    }

    // AuthenticationManager : ProviderManager의 인스턴스 -> Provider 순회 인증시도
    return this.getAuthenticationManager().authenticate(new JwtAuthenticationToken(
        loginRequestDto.username,
        loginRequestDto.password,
        loginRequestDto.csrfToken
    ));
  }

  // json에서 email로 오는 값을 username으로 처리
  public record LoginRequestDto(@JsonProperty("email") String username, String password, @JsonProperty("_csrf") String csrfToken, String captchaAnswer) {

  }
}
