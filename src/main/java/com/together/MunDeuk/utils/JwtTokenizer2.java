package com.together.MunDeuk.utils;

import com.together.MunDeuk.exception.CustomExpiredJwtException;
import com.together.MunDeuk.exception.CustomJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtTokenizer2 {
  @Value("${jwt.secret}")
  public String secretKey;
//  // static 분리
//  public static final String secretKey = "JWTsecretkeyforexampleJWTsecretkeyforexampleJWTsecretkeyforexample";

//  public static final String JWT_HEADER = "Authorization";

//  // static 분리
//  public static final String ACCESS_HEADER = "Access";
  @Value("${jwt.access-header}")
  public String ACCESS_HEADER;

//  // static 분리
//  public static final String REFRESH_HEADER = "Refresh";
  @Value("${jwt.refresh-header}")
  public String REFRESH_HEADER;

//  public static final String JWT_PREFIX = "Bearer ";

//  // static 분리
//  public static final int ACCESS_EXP_TIME = 10;   // 10분
  @Value("${jwt.access-exp-time}")
  public int ACCESS_EXP_TIME;
//  // static 분리
//  public static final int REFRESH_EXP_TIME = 60 * 24;   // 24시간
//  public static final int REFRESH_EXP_TIME = 10;
  @Value("${jwt.refresh-exp-time}")
  public int REFRESH_EXP_TIME;

//  public static final String encodedKey = Base64.getEncoder().encodeToString(secretKey.getBytes());

  private Key key;

//  public static String getTokenFromHeader(String header) {
//    return header.split(" ")[1];
//  }

  // static 분리
  public String generateToken(Map<String, Object> valueMap, int validTime) {
    try {
      key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    } catch(Exception e){
      throw new RuntimeException(e.getMessage());
    }
    return Jwts.builder()
        .setHeader(Map.of("typ","JWT"))
        .setClaims(valueMap)
        .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
        .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(validTime).toInstant()))
        .signWith(key)
        .compact();
  }

  // static 분리
  public Claims validateToken(String token) {
    Claims claim;
    try {
      SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
      claim = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token) // 파싱 및 검증, 실패 시 에러
          .getBody();
    } catch(ExpiredJwtException expiredJwtException){
      throw new CustomExpiredJwtException("토큰이 만료되었습니다", expiredJwtException);
    } catch(Exception e){
      log.debug("reason : {}" + e);
      throw new CustomJwtException("Error");
    }
    return claim;
  }

  // static 분리
  // 토큰이 만료되었는지 판단하는 메서드
  public boolean isExpired(String token) {
    try {
      Claims claims = this.validateToken(token);
      Date tokenTime = claims.getExpiration();
      if (tokenRemainTime(tokenTime) > 0) {
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      return (e instanceof CustomExpiredJwtException);
    }
  }

  public static long tokenRemainTime(Date expTime) {
    Date expDate = expTime;
    long remainMs = expDate.getTime() - System.currentTimeMillis();
    return remainMs / (1000 * 60);
  }

  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, claims -> claims.get("name", String.class));
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = validateToken(token);
    return claimsResolver.apply(claims);
  }

}
