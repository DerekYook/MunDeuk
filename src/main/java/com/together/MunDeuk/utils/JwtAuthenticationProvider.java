package com.together.MunDeuk.utils;

import com.together.MunDeuk.web.Member.service.CustomUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

@Slf4j
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final CustomUserDetailService customUserDetailService;

  public JwtAuthenticationProvider(CustomUserDetailService customUserDetailService) {
    log.info("---- Action JWT Authentication Provider ----");
    this.customUserDetailService = customUserDetailService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    log.info("add provider : " + authentication);
    try {
      // 로그인 인증 전
      JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
      String principal = (String) token.getPrincipal();
      String credential = (String) token.getCredentials();

      UserDetails userDetails = customUserDetailService.loadUserByUsername(principal);

      String role = String.valueOf(userDetails.getAuthorities());

      // 로그인 인증 후
      JwtAuthenticationToken authenticated
          = new JwtAuthenticationToken(userDetails.getUsername(), credential,
          AuthorityUtils.createAuthorityList(
              // todo : redirect issue
              String.valueOf(userDetails.getAuthorities())));
      authenticated.setDetails(userDetails);
      return authenticated;
    } catch (IllegalArgumentException e) {
      throw new BadCredentialsException(e.getMessage());
    } catch (DataAccessException e) {
      throw new AuthenticationServiceException(e.getMessage());
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return ClassUtils.isAssignable(JwtAuthenticationToken.class, authentication);
  }

}
