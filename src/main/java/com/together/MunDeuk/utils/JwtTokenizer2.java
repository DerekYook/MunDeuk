package com.together.MunDeuk.utils;

import com.together.MunDeuk.exception.CustomExpiredJwtException;
import com.together.MunDeuk.exception.CustomJwtException;
import com.together.MunDeuk.web.Member.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import javax.crypto.SecretKey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtTokenizer2 {
  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String REFRESH_HEADER = "Refresh";
  public static final String BEARER_PREFIX = "Bearer ";

  public static final String secretKey = "JWTsecretkey_for-exampleJWTsecretkey_for-exampleJWTsecretkey_for-example";
  public static final int ACCESS_EXP_TIME = 10;   // 10분
//  public static final int REFRESH_EXP_TIME = 60 * 24;   // 24시간
  public static final int REFRESH_EXP_TIME = 10;

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

  public static Authentication getAuthentication(String token) {
    Map<String, Object> claims = validateToken(token);

    System.out.println("+++++++++++++");
    System.out.println(claims);

    String email = (String) claims.get("email");
    String name = (String) claims.get("name");
    String role = (String) claims.get("role");

//    Member member = ;
//    Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(member.getRole().getValue()));
//    PrincipalDetail principalDetail = new PrincipalDetail(member, authorities);
//
//    return new UsernamePasswordAuthenticationToken(principalDetail, "", authorities);
    return null;
  }

  public static Map<String, Object> validateToken(String token) {
    Map<String, Object> claim = null;
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
      validateToken(token);
    } catch (Exception e) {
      return (e instanceof CustomExpiredJwtException);
    }
    return false;
  }

  // 토큰의 남은 만료시간 계산
  public static long tokenRemainTime(Integer expTime) {
    Date expDate = new Date((long) expTime * (1000));
    long remainMs = expDate.getTime() - System.currentTimeMillis();
    return remainMs / (1000 * 60);
  }

  public static void setAccessTokenHeader(String accessToken, HttpServletResponse response){
    String headerValue = BEARER_PREFIX + accessToken;
    response.setHeader(AUTHORIZATION_HEADER, headerValue);
  }

  public static void setRefreshTokenHeader(String refreshToken, HttpServletResponse response){
    String headerValue = BEARER_PREFIX + refreshToken;
    response.setHeader(REFRESH_HEADER, headerValue);
  }

}
