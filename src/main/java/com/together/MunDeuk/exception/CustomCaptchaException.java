package com.together.MunDeuk.exception;

import org.springframework.security.core.AuthenticationException;

public class CustomCaptchaException extends AuthenticationException {

  public CustomCaptchaException(String msg, Throwable t) {
    super(msg, t);
  }

  public CustomCaptchaException(String msg) {
    super(msg);
  }

//  public CustomCaptchaException() {
//    super();
//  }

}
