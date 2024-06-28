package com.together.MunDeuk.utils;

import jakarta.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

//@Component
public class CustomAuthorizationRequestResolver{
//public class CustomAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {
//
//  private final OAuth2AuthorizationRequestResolver defaultAuthorizationRequestResolver;
//
//  public CustomAuthorizationRequestResolver(
//      ClientRegistrationRepository clientRegistrationRepository) {
//
//    this.defaultAuthorizationRequestResolver =
//        new DefaultOAuth2AuthorizationRequestResolver(
//            clientRegistrationRepository, "/oauth2/authorization");
//  }
//
//  @Override
//  public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
//    OAuth2AuthorizationRequest authorizationRequest =
//        this.defaultAuthorizationRequestResolver.resolve(request);
//
//    return authorizationRequest != null ?
//        customAuthorizationRequest(authorizationRequest) :
//        null;
//  }
//
//  @Override
//  public OAuth2AuthorizationRequest resolve(
//      HttpServletRequest request, String clientRegistrationId) {
//
//    OAuth2AuthorizationRequest authorizationRequest =
//        this.defaultAuthorizationRequestResolver.resolve(
//            request, clientRegistrationId);
//
//    return authorizationRequest != null ?
//        customAuthorizationRequest(authorizationRequest) :
//        null;
//  }
//
//  private OAuth2AuthorizationRequest customAuthorizationRequest(
//      OAuth2AuthorizationRequest authorizationRequest) {
//
//    Map<String, Object> additionalParameters =
//        new LinkedHashMap<>(authorizationRequest.getAdditionalParameters());
//
//    String registrationId = authorizationRequest.getAttribute("registration_id");
//
//    String prompt = "";
//    if (registrationId.equals("naver")) {
//      prompt = "login";
//    } else if (registrationId.equals("google")) {
//      prompt = "select_account";
//    }
//    additionalParameters.put("prompt", prompt);
//
//    return OAuth2AuthorizationRequest.from(authorizationRequest)
//        .additionalParameters(additionalParameters)
//        .build();
//  }
}
