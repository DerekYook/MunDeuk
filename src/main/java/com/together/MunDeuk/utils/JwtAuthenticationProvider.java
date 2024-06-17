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
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

//public class JwtAuthenticationProvider {
@Slf4j
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final CustomOAuth2UserService customOAuth2UserService;

  public JwtAuthenticationProvider(CustomOAuth2UserService customOAuth2UserService) {
    log.info("---- Action Authentication Provider ----");
    this.customOAuth2UserService = customOAuth2UserService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    log.info("add provider : " + authentication);
//    try {
//      // 로그인 인증 전
//      JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
//      String principal = (String) authenticationToken.getPrincipal();
//      String credential = (String) authenticationToken.getCredentials();
//      System.out.println(principal);
//      System.out.println(credential);
////      AuthResponseDto authResponseDto = customOAuth2UserService.loadUser(new AuthRequestDto(principal, credential));
//      // 로그인 인증 후
////      JwtAuthenticationToken authenticated
////          = new JwtAuthenticationToken(authResponseDto.getUser().getId(), null, AuthorityUtils.createAuthorityList("ROLE_USER"));
////      authenticated.setDetails(authResponseDto);
////      return authenticated;
//        return authenticationToken;
////    } catch(DoNotExistException e) {
////      throw new UsernameNotFoundException(e.getMessage());
//    } catch(IllegalArgumentException e) {
//      throw new BadCredentialsException(e.getMessage());
//    } catch(DataAccessException e) {
//      throw new AuthenticationServiceException(e.getMessage());
//    }
    OAuth2LoginAuthenticationToken token = (OAuth2LoginAuthenticationToken) authentication;
    log.debug("info oauth2 token : " + token.getCredentials());
    OAuth2UserRequest userRequest = new OAuth2UserRequest(
        token.getClientRegistration(),
//        token.getAuthorizationExchange()
        token.getAccessToken()
    );
    OAuth2User user = customOAuth2UserService.loadUser(userRequest);
    return new OAuth2LoginAuthenticationToken(
        token.getClientRegistration(),
        token.getAuthorizationExchange(),
        user,
        user.getAuthorities(),
//        user.getAttributes()
        token.getAccessToken()
    );
  }

  @Override
  public boolean supports(Class<?> authentication) {
//    return ClassUtils.isAssignable(JwtAuthenticationToken.class, authentication);
    return OAuth2LoginAuthenticationToken.class.isAssignableFrom(authentication);
  }

}
