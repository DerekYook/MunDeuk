package com.together.MunDeuk.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.together.MunDeuk.web.OAuth2.dto.CustomOAuth2TokenResponseDto;
import com.together.MunDeuk.web.OAuth2.service.CustomOAuth2UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationExchange;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationResponse;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


//public class OAuth2LoginAuthenticationProvider {

@Slf4j
@Component
public class OAuth2LoginAuthenticationProvider implements AuthenticationProvider {
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

  private final CustomOAuth2UserService customOAuth2UserService;

  public OAuth2LoginAuthenticationProvider(CustomOAuth2UserService customOAuth2UserService) {
    log.info("---- Action OAuth2 Authentication Provider ----");
    this.customOAuth2UserService = customOAuth2UserService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    log.info("add provider : " + authentication);

    OAuth2LoginAuthenticationToken token = (OAuth2LoginAuthenticationToken) authentication;
    log.debug("info oauth2 token : " + token.getCredentials());

    OAuth2AuthorizationExchange authorizationExchange = token.getAuthorizationExchange();
    OAuth2AuthorizationResponse authorizationResponse = authorizationExchange.getAuthorizationResponse();

    // Authorization Code를 사용하여 Access Token 요청
    OAuth2AccessToken accessToken = getAccessToken(
        token.getClientRegistration().getProviderDetails().getTokenUri(),
        token.getClientRegistration().getClientId(),
        token.getClientRegistration().getClientSecret(),
        token.getClientRegistration().getRedirectUri(),
        authorizationResponse.getCode()
    );

    OAuth2UserRequest userRequest = new OAuth2UserRequest(
        token.getClientRegistration(),
        accessToken
    );

    OAuth2User user = customOAuth2UserService.loadUser(userRequest);

    return new OAuth2LoginAuthenticationToken(
        token.getClientRegistration(),
        token.getAuthorizationExchange(),
        user,
        user.getAuthorities(),
        accessToken
    );
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return OAuth2LoginAuthenticationToken.class.isAssignableFrom(authentication);
  }

  private OAuth2AccessToken getAccessToken(String tokenUri, String clientId, String clientSecret,
      String redirectUri, String code) {
    RestTemplate restTemplate = new RestTemplate();

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", clientId);
    params.add("client_secret", clientSecret);
    params.add("redirect_uri", redirectUri);
    params.add("code", code);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

    ResponseEntity<CustomOAuth2TokenResponseDto> response = restTemplate.exchange(
        tokenUri, HttpMethod.POST, request, CustomOAuth2TokenResponseDto.class
    );

//    ResponseEntity<String> response = restTemplate.exchange(
//        tokenUri, HttpMethod.POST, request, String.class
//    );

    if (response.getStatusCode() == HttpStatus.OK) {
      try {
        // 로그에 응답 본문을 출력하여 디버깅
        log.debug("Token Response: " + response.getBody());

////        // JSON 파싱을 위해 ObjectMapper 사용
////        ObjectMapper mapper = new ObjectMapper();
////        OAuth2AccessTokenResponse tokenResponse = mapper.readValue(response.getBody(), OAuth2AccessTokenResponse.class);
//        OAuth2AccessTokenResponse tokenResponse = response.getBody();

        CustomOAuth2TokenResponseDto tokenResponse = response.getBody();

//        return tokenResponse.getAccessToken();
        if (tokenResponse != null) {
          return new OAuth2AccessToken(
              OAuth2AccessToken.TokenType.BEARER,
              tokenResponse.getAccessToken(),
              null,  // issuedAt 값을 설정할 수 있으면 설정
              null   // expiresAt 값을 설정할 수 있으면 설정
          );
        } else {
          log.error("Failed to obtain access token: response body is null");
          return null;
        }
//        ObjectMapper mapper = new ObjectMapper();
//        OAuth2AccessTokenResponse tokenResponse = mapper.readValue(response.getBody(), OAuth2AccessTokenResponse.class);
//        return tokenResponse.getAccessToken();

      } catch (Exception e) {
        log.error("Failed to parse access token response", e);
        return null;
      }
    } else {
      log.error("Failed to obtain access token, status code: " + response.getStatusCode());
      log.error("Response body: " + response.getBody());
      return null;
    }
  }
}

