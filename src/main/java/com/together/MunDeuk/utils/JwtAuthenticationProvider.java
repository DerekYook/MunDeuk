package com.together.MunDeuk.utils;

import com.together.MunDeuk.web.OAuth2.dto.AuthRequestDto;
import com.together.MunDeuk.web.OAuth2.dto.AuthResponseDto;
import com.together.MunDeuk.web.OAuth2.service.CustomOAuth2UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

//public class JwtAuthenticationProvider {
@Slf4j
//@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final CustomOAuth2UserService customOAuth2UserService;

  public JwtAuthenticationProvider(CustomOAuth2UserService customOAuth2UserService) {
    log.info("---- Action Authentication Provider ----");
    this.customOAuth2UserService = customOAuth2UserService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    try {
      // 로그인 인증 전
      JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
      String principal = (String) authenticationToken.getPrincipal();
      String credential = (String) authenticationToken.getCredentials();
      System.out.println(principal);
      System.out.println(credential);
//      AuthResponseDto authResponseDto = customOAuth2UserService.loadUser(new AuthRequestDto(principal, credential));
      // 로그인 인증 후
//      JwtAuthenticationToken authenticated
//          = new JwtAuthenticationToken(authResponseDto.getUser().getId(), null, AuthorityUtils.createAuthorityList("ROLE_USER"));
//      authenticated.setDetails(authResponseDto);
//      return authenticated;
        return authenticationToken;
//    } catch(DoNotExistException e) {
//      throw new UsernameNotFoundException(e.getMessage());
    } catch(IllegalArgumentException e) {
      throw new BadCredentialsException(e.getMessage());
    } catch(DataAccessException e) {
      throw new AuthenticationServiceException(e.getMessage());
    }
  }
  @Override
  public boolean supports(Class<?> authentication) {
    return ClassUtils.isAssignable(JwtAuthenticationToken.class, authentication);
  }
}
