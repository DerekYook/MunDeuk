package com.together.MunDeuk.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaptchaConfig {

  @Bean
  public DefaultKaptcha getKaptchaBean() {
    DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
    Properties properties = new Properties();
    properties.setProperty("kaptcha.border", "no");
    properties.setProperty("kaptcha.textproducer.font.color", "black");
    properties.setProperty("kaptcha.textproducer.char.space", "5");
    Config config = new Config(properties);
    defaultKaptcha.setConfig(config);
    return defaultKaptcha;
  }

}
