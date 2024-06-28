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
//  @Value("${jwt.secret}")
//  public String secret;
  public static final String JWT_HEADER = "Authorization";
  public static final String ACCESS_HEADER = "Access";
  public static final String REFRESH_HEADER = "Refresh";
  public static final String JWT_PREFIX = "Bearer ";
  public static final String secretKey = "JWTsecretkeyforexampleJWTsecretkeyforexampleJWTsecretkeyforexample";
  public static final int ACCESS_EXP_TIME = 10;   // 10분
//  public static final int REFRESH_EXP_TIME = 60 * 24;   // 24시간
  public static final int REFRESH_EXP_TIME = 10;
//  public static final String encodedKey = Base64.getEncoder().encodeToString(secretKey.getBytes());

  private static Key key;

  public static String getTokenFromHeader(String header) {
    return header.split(" ")[1];
  }

  public static String generateToken(Map<String, Object> valueMap, int validTime) {
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

  public static Claims validateToken(String token) {
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
      throw new CustomJwtException("Error");
    }
    return claim;
  }

  // 토큰이 만료되었는지 판단하는 메서드
  public static boolean isExpired(String token) {
    try {
      Claims claims = validateToken(token);
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

  private Claims getAllClaimsForToken(String token) {
//    System.out.println(secret);
    System.out.println(secretKey);
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
  }
}
