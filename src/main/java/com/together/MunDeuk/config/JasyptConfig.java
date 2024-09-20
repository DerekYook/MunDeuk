package com.together.MunDeuk.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableEncryptableProperties
public class JasyptConfig {

  @Bean("jasyptStringEncryptor")
  public StringEncryptor stringEncryptor(){
    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
    config.setPoolSize("1");
    encryptor.setPassword(getJasyptEncryptorPassword()); // 암호화 키
    config.setAlgorithm("PBEWithMD5AndDES"); // 사용알고리즘
    config.setStringOutputType("base64"); // 암호화 이후 받을 값의 형태를 설정
    config.setKeyObtentionIterations("1000"); // 암호화 키를 얻기위해 반복하는 해시 횟수
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    encryptor.setConfig(config);
    return encryptor;
  }

  private String getJasyptEncryptorPassword() {
    try {
      ClassPathResource resource = new ClassPathResource("jasypt-encryptor-password.txt");
      return Files.readAllLines(Paths.get(resource.getURI())).stream().collect(Collectors.joining(""));
    } catch (IOException e) {
      throw new RuntimeException("Not found Jasypt password File!!");
    }
  }
}
