package com.together.MunDeuk.exception;

public class CustomOAuth2Exception extends RuntimeException {

  public CustomOAuth2Exception(String msg, Throwable t) {
    super(msg, t);
  }

  public CustomOAuth2Exception(String msg) {
    super(msg);
  }

  public CustomOAuth2Exception() {
    super();
  }

}
