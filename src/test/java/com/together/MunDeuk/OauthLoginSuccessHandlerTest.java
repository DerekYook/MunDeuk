//package com.together.MunDeuk;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyMap;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.together.MunDeuk.utils.CookieUtil;
//import com.together.MunDeuk.utils.CustomOauth2LoginSuccessHandler2;
//import com.together.MunDeuk.utils.JwtTokenizer2;
//import com.together.MunDeuk.web.Member.entity.Member;
//import com.together.MunDeuk.web.OAuth2.domain.OAuth2PrincipalDetail;
//import io.jsonwebtoken.Jwt;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import org.assertj.core.api.Assertions;
//import org.jetbrains.annotations.NotNull;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseCookie;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.web.DefaultRedirectStrategy;
//import org.springframework.security.web.RedirectStrategy;
//
//import java.io.IOException;
//
//public class OauthLoginSuccessHandlerTest {
//
//  @Mock
//  private HttpServletRequest request;
//
//  @Mock
//  private HttpServletResponse response;
//
//  @Mock
//  private Authentication authentication;
//
//  @Mock
//  private RedirectStrategy redirectStrategy;
//
//  @Mock
//  private OAuth2PrincipalDetail oAuth2PrincipalDetail;
//
//  @Mock
//  private CookieUtil cookieUtil;
//
//  @Mock
//  private ResponseCookie responseCookie;
//
//  @Mock
//  private JwtTokenizer2 jwtTokenizer2;
//
//  @InjectMocks
//  private CustomOauth2LoginSuccessHandler2 handler;
//
//  @BeforeEach
//  public void setup() {
//    MockitoAnnotations.openMocks(this);
//  }
//
//  @Test
//  public void testOnAuthenticationSuccess_UserRole() throws IOException, ServletException {
//    // given
//    HttpServletRequest request = mock(HttpServletRequest.class);
//    HttpServletResponse response = mock(HttpServletResponse.class);
//    Authentication authentication = mock(Authentication.class);
//
//    // when
//    when(authentication.getPrincipal()).thenReturn(oAuth2PrincipalDetail); // 여기서 필요한 객체로 대체
//    when(authentication.isAuthenticated()).thenReturn(true);
//    when(cookieUtil.createCookieByUserId(anyString(), anyString())).thenReturn(responseCookie);
//
//    // 권한 모의 설정
//    Collection<GrantedAuthority> authorities = new ArrayList<>();
//    authorities.add(new SimpleGrantedAuthority("User"));
//    when(authentication.getAuthorities()).thenReturn((Collection) authorities);
//
//    when(request.getHeader("X-Requested-With")).thenReturn(null);
//
//    handler.onAuthenticationSuccess(request, response, authentication);
//
//    // then
//    verify(redirectStrategy).sendRedirect(request, response, "/oauthRedirect?loginSuccess=true");
//  }
//
//  @Test
//  public void testOnAuthenticationSuccess_InvalidRole() throws Exception {
//    // given
//    HttpServletRequest request = mock(HttpServletRequest.class);
//    HttpServletResponse response = mock(HttpServletResponse.class);
//    Authentication authentication = mock(Authentication.class);
//
//    // when
//    when(authentication.getPrincipal()).thenReturn(oAuth2PrincipalDetail); // 여기서 필요한 객체로 대체
//    when(authentication.isAuthenticated()).thenReturn(true);
////    when()
//    when(cookieUtil.createCookieByUserId(anyString(), anyString())).thenReturn(responseCookie);
//    response.addHeader(anyString(),anyString());
//
//    // 권한 모의 설정
//    Collection<GrantedAuthority> authorities = new ArrayList<>();
//    authorities.add(new SimpleGrantedAuthority("[ROLE_Invalid]"));
//    when(authentication.getAuthorities()).thenReturn((Collection) authorities);
//
//    // then
//    assertThrows(IllegalStateException.class, () -> {
//      // when
//      handler.onAuthenticationSuccess(request, response, authentication);
//    });
//  }
//
//}
