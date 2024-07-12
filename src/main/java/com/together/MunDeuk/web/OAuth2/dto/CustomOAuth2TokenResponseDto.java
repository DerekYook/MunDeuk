package com.together.MunDeuk.web.OAuth2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomOAuth2TokenResponseDto {
  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("token_type")
  private String tokenType;

  @JsonProperty("refresh_token")
  private String refreshToken;

  @JsonProperty("expires_in")
  private long expiresIn;

  @JsonProperty("refresh_token_expires_in")
  private int refreshTokenExpiresIn;

//  @JsonProperty("scope")
//  private Map<String, Object> scope;
}
