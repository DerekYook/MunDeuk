package com.together.MunDeuk.web.OAuth2.entity;

import java.util.Map;

public class OAuth2UserInfo {

  public static String socialId;
  public static Map<String, Object> account;
  public static Map<String, Object> profile;
  public static String email;
  public OAuth2UserInfo (Map<String, Object> attributes) {
    socialId = String.valueOf(attributes.get("id"));
    account = (Map<String, Object>) attributes.get("kakao_account");
    profile = (Map<String, Object>) account.get("profile");
    email = String.valueOf(account.get("email"));
  }
  public String getSocialId() {
    return socialId;
  }

  public String getName() {
    return String.valueOf(profile.get("nickname"));
  }

  public String getEmail(){
    return email;
  }
}
