package com.together.MunDeuk.utils;

import com.together.MunDeuk.web.Member.dto.MemberDto;
import com.together.MunDeuk.web.Member.entity.Member.MemberAuth;
import com.together.MunDeuk.web.Member.service.CustomUserDetailService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenizer {
  public static final String BEARER_TYPE = "Bearer";
  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String REFRESH_HEADER = "Refresh";
  public static final String BEARER_PREFIX = "Bearer";

  // secretKey가져오기
  @Getter
  @Value("${jwt.secret}")
  private String secretKey;

  // accessToken만료시간
  @Getter
  @Value("${jwt.access-token-expiration-millis}")
  private long accessTokenExpirationMillis;

  // refreshToken만료시간
  @Getter
  @Value("${jwt.refress-token-expiration-millis}")
  private long refreshTokenExpirationMillis;

  private Key key;

  @PostConstruct
  public void init() {
    String base64EncodedSecretKey = encodeBase64URLSecretKey(this.secretKey);
    this.key = getKeyFromBase64URLEncodedKey(base64EncodedSecretKey);
  }

  // 인코딩
  public String encodeBase64URLSecretKey(String secretKey) {
    return Encoders.BASE64URL.encode(secretKey.getBytes(StandardCharsets.UTF_8));
  }

  // 디코딩
  private Key getKeyFromBase64URLEncodedKey(String base64EncodedSecretKey) {
    byte[] keyBytes = Decoders.BASE64URL.decode(base64EncodedSecretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String generateToken(String email, MemberAuth memberAuth) {
    return Jwts.builder().setSubject(email).claim("role", memberAuth).setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(Date.from(Instant.from(Instant.now().plus(accessTokenExpirationMillis,
            ChronoUnit.MILLIS))))
        .signWith(SignatureAlgorithm.HS256,secretKey).compact();
  }
}
