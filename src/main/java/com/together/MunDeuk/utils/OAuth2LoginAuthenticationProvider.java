package com.together.MunDeuk.utils;

import com.together.MunDeuk.web.OAuth2.service.CustomOAuth2UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class OAuth2LoginAuthenticationProvider {
//public class OAuth2LoginAuthenticationProvider implements AuthenticationProvider {
//
//  private final OAuth2UserService<OAuth2UserRequest, OAuth2User> userService;
//
//  public OAuth2LoginAuthenticationProvider(OAuth2UserService<OAuth2UserRequest, OAuth2User> userService) {
//    this.userService = userService;
//  }
//
//  @Override
//  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//    OAuth2LoginAuthenticationToken token = (OAuth2LoginAuthenticationToken) authentication;
//    OAuth2UserRequest userRequest = new OAuth2UserRequest(
//        token.getClientRegistration(),
////        token.getAuthorizationExchange()
//        token.getAccessToken()
//    );
////    CustomOAuth2UserService customOAuth2UserService = new CustomOAuth2UserService();
//    OAuth2User user = userService.loadUser(userRequest);
//    return new OAuth2LoginAuthenticationToken(
//        token.getClientRegistration(),
//        token.getAuthorizationExchange(),
//        user,
//        user.getAuthorities(),
////        user.getAttributes()
//        token.getAccessToken()
//    );
//  }
//
//  @Override
//  public boolean supports(Class<?> authentication) {
//    return OAuth2LoginAuthenticationToken.class.isAssignableFrom(authentication);
//  }
}

