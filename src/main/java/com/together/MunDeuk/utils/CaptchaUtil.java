package com.together.MunDeuk.utils;

import org.springframework.stereotype.Component;

@Component
public class CaptchaUtil {

  public boolean validateCaptcha(String captchaId, String captchaAnswer) {
    return captchaId != null && captchaId.equals(captchaAnswer);
  }
}
