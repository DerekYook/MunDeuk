package com.together.MunDeuk.exception;

import com.together.MunDeuk.utils.JwtAuthenticationToken;
import com.together.MunDeuk.utils.JwtTokenizer2;

public class CustomOAuth2Exception extends RuntimeException {

  private String token;

  public CustomOAuth2Exception(String msg, Throwable t) {
    super(msg, t);
  }

  public CustomOAuth2Exception(String msg, String token) {
    super(msg);
    this.token = token;
  }

  public String getToken(){
    return token;
  }

  public CustomOAuth2Exception(String msg) {
    super(msg);
  }

  public CustomOAuth2Exception() {
    super();
  }

}
