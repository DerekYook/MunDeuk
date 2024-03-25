package com.together.MunDeuk.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
  protected Log logger = LogFactory.getLog(this.getClass());

  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Override
  public void onAuthenticationSuccess (HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {
    handle(request, response, authentication);
    clearAuthenticationAttributes(request);
//    response.setContentType("application/json;charset=UTF-8");
//    response.setStatus(HttpServletResponse.SC_OK);
//
//    Map<String, Object> responseData = new HashMap<>();
//    if (isAdmin(authentication)) {
//      responseData.put("status", "AdminSuccess");
//    } else {
//      responseData.put("status", "MemberSuccess");
//    }
//
//    new ObjectMapper().writeValue(response.getWriter(), responseData);
  }

  private boolean isAdmin(Authentication authentication) {
    return authentication.getAuthorities().stream()
        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
  }

  protected void handle(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {
    String targetUrl = determineTargetUrl(authentication);

    if(response.isCommitted()) {
      logger.debug("Response has already been committed. Unable to redirect to "
          + targetUrl);
      return;
    }
    System.out.println("11111111");
    System.out.println(request);
    System.out.println("22222222");
    System.out.println(response);
    System.out.println("333333333");
    System.out.println(targetUrl);

    redirectStrategy.sendRedirect(request, response, targetUrl);
  }
  protected String determineTargetUrl(final Authentication authentication) {

    Map<String, String> roleTargetUrlMap = new HashMap<>();
    roleTargetUrlMap.put("ROLE_USER", "/mains");
    roleTargetUrlMap.put("ROLE_ADMIN", "/admin/mains");

    final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    for (final GrantedAuthority grantedAuthority : authorities) {
      String authorityName = grantedAuthority.getAuthority();
      if(roleTargetUrlMap.containsKey(authorityName)) {
        return roleTargetUrlMap.get(authorityName);
      }
    }

    throw new IllegalStateException();
  }

  protected void clearAuthenticationAttributes(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return;
    }
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  }
}
