package com.together.MunDeuk.utils;

import com.together.MunDeuk.web.Member.service.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
@RequiredArgsConstructor
// JWT 토큰 인증
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final CustomUserDetailService customUserDetailService;
  private final JwtTokenizer jwtTokenizer;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException{
    // access토큰 정보 추출
    String token = jwtTokenizer.resolveAccessToken(request);

    // 토큰 검증
    if(token != null && jwtTokenizer.validateToken(token)){
      // 토큰에서 유저정보 추출
      String email = jwtTokenizer.parseClaim(token).getSubject();

      // 사용자 인증
      UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
      if (userDetails != null) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
        log.info("authenticated user with email : {}", email);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    filterChain.doFilter(request, response);
  }
}
