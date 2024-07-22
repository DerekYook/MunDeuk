package com.together.MunDeuk.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.Constants;
import com.together.MunDeuk.exception.CustomCaptchaException;
import com.together.MunDeuk.web.Member.entity.Member;
import com.together.MunDeuk.web.Member.repository.MemberRepository;
import com.together.MunDeuk.web.Member.service.MemberService;
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
  private final MemberService memberService;

  public LoginAuthenticationFilter(final String defaultFilterProcessesUrl,
      final AuthenticationManager authenticationManager, CaptchaUtil captchaUtil, MemberService memberService) {
    super(defaultFilterProcessesUrl, authenticationManager);
    this.captchaUtil = captchaUtil;
    this.memberService = memberService;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws IOException, CustomCaptchaException {
    String method = request.getMethod();

    if (!method.equals("POST")) {
      throw new AuthenticationServiceException(
          "Authentication method not supported: " + request.getMethod());
    }

    ServletInputStream inputStream = request.getInputStream();

    LoginRequestDto loginRequestDto = new ObjectMapper().readValue(inputStream,
        LoginRequestDto.class);

    String userName = loginRequestDto.username;
    String password = loginRequestDto.password;
    System.out.println(userName);
    System.out.println(password);

    try {
      memberService.loginMember(userName, password);

      // captcha 검증
//    String captchaAnswer = request.getParameter("captchaAnswer");
      String captchaAnswer = loginRequestDto.captchaAnswer;
      String captchaId = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

      if (!captchaUtil.validateCaptcha(captchaId, captchaAnswer)) {
        logger.info("Captcha Validate Fail : Provided Text : " + captchaId + " / User Text : " + captchaAnswer);
        throw new CustomCaptchaException("INVALID_CAPTCHA");
      } else {
        logger.info("CAPTCHA validated successfully");
      }

      // AuthenticationManager : ProviderManager의 인스턴스 -> Provider 순회 인증시도
      return this.getAuthenticationManager().authenticate(new JwtAuthenticationToken(
          loginRequestDto.username,
          loginRequestDto.password,
          loginRequestDto.csrfToken
      ));
    } catch (NullPointerException e) {
      throw new CustomCaptchaException("INVALID_ACCOUNT");
    }
  }

  // json에서 email로 오는 값을 username으로 처리
  public record LoginRequestDto(@JsonProperty("email") String username, String password, @JsonProperty("_csrf") String csrfToken, String captchaAnswer) {

  }
}
