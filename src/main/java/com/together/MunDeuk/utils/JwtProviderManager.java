package com.together.MunDeuk.utils;

import com.together.MunDeuk.web.OAuth2.service.CustomOAuth2UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Slf4j
public class JwtProviderManager implements AuthenticationManager {

  @Value("${jwt.secret}")
  private String secretKey;
  private final CustomOAuth2UserService customOAuth2UserService;

  public JwtProviderManager(CustomOAuth2UserService customOAuth2UserService) {
    this.customOAuth2UserService = customOAuth2UserService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String token = (String) authentication.getCredentials();
    log.info("---- Custom Authentication Manager ----");
    try {
      Claims claims = Jwts.parser()
          .setSigningKey(secretKey)
          .parseClaimsJws(token)
          .getBody();
      String authPri = (String) authentication.getPrincipal();
      String authDet = (String) authentication.getDetails();

      System.out.println(authPri);
      System.out.println(authDet);
//      OAuth2UserRequest userRequest = ;
//
//      OAuth2PrincipalDetail userDetails = customOAuth2UserService.loadUser(userRequest);
//
//      return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
      return null;

    } catch (Exception e) {
      throw new BadCredentialsException("Invalid token");
    }
  }
}
