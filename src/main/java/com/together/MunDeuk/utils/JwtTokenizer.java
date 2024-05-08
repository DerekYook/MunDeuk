package com.together.MunDeuk.utils;

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
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtTokenizer {
//  public static final String AUTHORIZATION_HEADER = "Authorization";
//  public static final String REFRESH_HEADER = "Refresh";
//  public static final String BEARER_PREFIX = "Bearer";
//
//  // secretKey가져오기
//  @Getter
//  // @Value import주의
//  @Value("${jwt.secret}")
//  private String secretKey;
//
//  // accessToken만료시간
//  @Getter
//  @Value("${jwt.access-token-expiration-millis}")
//  private long accessTokenExpirationMillis;
//
//  // refreshToken만료시간
//  @Getter
//  @Value("${jwt.refresh-token-expiration-millis}")
//  private long refreshTokenExpirationMillis;
//
//  private Key key;
//
//  // bean등록 후 Key SecretKey HS알고리즘 decode
//  // @PostConstruct는 의존성 주입이 이루어진 후 초기화를 수행하는 메서드이다.
//  // @PostConstruct가 붙은 메서드는 @Component, @Service, @Repository, @Controller 클래스가 Spring Bean에 등록되기 전에 발생한다.
//  // 이 메서드는 다른 리소스에서 호출되지 않는다해도 수행된다.
//  // bean이 초기화 됨과 동시에 의존성 확인 가능 = @Autowired나 @Value를 붙여 객체 사용시 사용 가능
//  // 실행 bean lifecycle 동안 오직 한번만 수행되는 것을 보장 = bean이 여러번 초기화 되는 것을 방지
//  @PostConstruct
//  public void init() {
//    String base64EncodedSecretKey = encodeBase64URLSecretKey(this.secretKey);
//    this.key = getKeyFromBase64URLEncodedKey(base64EncodedSecretKey);
//  }
//
//  // 인코딩
//  public String encodeBase64URLSecretKey(String secretKey) {
//    return Encoders.BASE64URL.encode(secretKey.getBytes(StandardCharsets.UTF_8));
//  }
//
//  // 디코딩
//  private Key getKeyFromBase64URLEncodedKey(String base64EncodedSecretKey) {
//    byte[] keyBytes = Decoders.BASE64URL.decode(base64EncodedSecretKey);
//    return Keys.hmacShaKeyFor(keyBytes);
//  }
//
//  //  public String generateToken(String email, MemberAuth memberAuth) {
////    return Jwts.builder().setSubject(email).claim("role", memberAuth).setIssuedAt(new Date(System.currentTimeMillis()))
////        .setExpiration(Date.from(Instant.from(Instant.now().plus(accessTokenExpirationMillis,
////            ChronoUnit.MILLIS))))
////        .signWith(SignatureAlgorithm.HS256,secretKey).compact();
////  }
//  public String generateAccessToken(Map<String, Object> claims, String subject,
//      Date expiration, String encodeBase64URLSecretKey) {
//    Key key = getKeyFromBase64URLEncodedKey(encodeBase64URLSecretKey);
//
//    return Jwts.builder()
//        // Custom Claims 추가 ( 인증된 사용자와 관련 정보 )
//        .setClaims(claims)
//        // JWT 제목 (사용자 구분용 도로 사용예정)
//        .setSubject(subject)
//        // accessToken의 발행 시간
//        .setIssuedAt(Calendar.getInstance().getTime())
//        // accessToken의 만료 시간
//        .setExpiration(expiration)
//        // 서명
//        .signWith(key)
//        // JWT생성 및 직렬화
//        .compact();
//  }
//
//  // AccessToken을 갱신하기에 Custom Claims가 필요없음
//  public String generateRefreshToken(String subject, Date expiration,
//      String encodeBase64URLSecretKey) {
//    Key key = getKeyFromBase64URLEncodedKey(encodeBase64URLSecretKey);
//
//    return Jwts.builder()
//        // JWT 제목 (사용자 구분용 도로 사용예정)
//        .setSubject(subject)
//        // refreshToken의 발행 시간
//        .setIssuedAt(Calendar.getInstance().getTime())
//        // refreshToken의 만료 시간
//        .setExpiration(expiration)
//        // 서명
//        .signWith(key)
//        // JWT생성 및 직렬화
//        .compact();
//  }
//
//  public void setAccessTokenHeader(String accessToken, HttpServletResponse response) {
//    String headerValue = BEARER_PREFIX + accessToken;
//    response.setHeader(AUTHORIZATION_HEADER, headerValue);
//  }
//
//  public void setRefreshTokenHeader(String refreshToken, HttpServletResponse response) {
//    response.setHeader(REFRESH_HEADER, refreshToken);
//  }
//
//  // token 복호화
//  public Claims parseClaim(String token) {
//    return Jwts.parserBuilder()
//        .setSigningKey(key)
//        .build()
//        .parseClaimsJws(token)
//        .getBody();
//  }
//
//  // Request Header에서 Access Token 정보 추출
//  public String resolveAccessToken(HttpServletRequest request) {
//    // Header값 추출
//    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//    // Header값 비교
//    if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
//      // Header 텍스트 제외하고 토큰 값 추출
//      return bearerToken.substring(7);
//    }
//    return null;
//  }
//
//  // Request Header에서 Refresh Token 정보 추출
//  public String resolveRefreshToken(HttpServletRequest request) {
//    // Header값 추출
//    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//    // Header값 비교
//    if(StringUtils.hasText(bearerToken)) {
//      // 토큰 값 추출
//      return bearerToken;
//    }
//    return null;
//  }
//
//  // 토큰 검증
//  public boolean validateToken(String token) {
//    try {
//      // parsing하여 정보 추출
//      parseClaim(token);
//    } catch (MalformedJwtException e) {
//      log.info("Invalid JWT token");
//      log.trace("Invalid JWT token trace = {}", e);
//    } catch (ExpiredJwtException e) {
//      log.info("Expired JWT token");
//      log.trace("Expired JWT token trace = {}", e);
//    } catch (UnsupportedJwtException e) {
//      log.info("Unsupported JWT token");
//      log.trace("Unsupported JWT token trace = {}", e);
//    } catch (IllegalArgumentException e) {
//      log.info("JWT claims string is empty.");
//      log.trace("JWT claims string is empty trace = {}", e);
//    }
//    return true;
//  }


}
