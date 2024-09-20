package com.together.MunDeuk.web.OAuth2.entity;

import java.util.Map;

public class GoogleUserInfo {

  public static String socialId;
  public static String picture;
  public static String email;

  public GoogleUserInfo(Map<String, Object> attributes) {
    socialId = String.valueOf(attributes.get("sub"));
    picture = String.valueOf(attributes.get("picture"));
    email = String.valueOf(attributes.get("email"));
  }

  public String getSocialId() {
    return socialId;
  }

  public String getPicture() {
    return picture;
  }

  public String getEmail(){
    return email;
  }
}
