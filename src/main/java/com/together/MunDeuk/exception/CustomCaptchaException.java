package com.together.MunDeuk.exception;

public class CustomCaptchaException extends RuntimeException {

  public CustomCaptchaException(String msg, Throwable t) {
    super(msg, t);
  }

  public CustomCaptchaException(String msg) {
    super(msg);
    System.out.println(this.getCause());
  }

  public CustomCaptchaException() {
    super();
  }

}
