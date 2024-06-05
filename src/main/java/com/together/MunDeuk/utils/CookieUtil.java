package com.together.MunDeuk.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import com.nimbusds.oauth2.sdk.Response;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class CookieUtil {

//  @Value("${site.domain}")
//  String siteDomain;

//	private Environment env;
//
//	public CookieUtil(Environment env) {
//		this.env = env;
//	}

  public ResponseCookie createCookie(String cookieName, String value){

    //log.info("siteDomain : "+env.getProperty("site.domain"));
    ResponseCookie token = ResponseCookie.from(cookieName, value) // key & value
        .httpOnly(true)		// prohibit js reading
        .secure(true)		// also transmit under http
        //.domain(env.getProperty("site.domain"))
        .path("/")			// path
        .maxAge((int)JwtTokenizer2.ACCESS_EXP_TIME)
        .sameSite("Lax")	// In most cases, third-party cookies are not sent, except for Get requests that navigate to the target URL
        .build();
//
//        Cookie token = new Cookie(cookieName,value);
//        token.setHttpOnly(true);
//        token.setMaxAge((int)JwtTokenUtil.TOKEN_VALIDATION_SECOND);
//        token.setPath("/");
    return token;
  }
  public ResponseCookie createCookieByUserId(String cookieName, String value) {
    ResponseCookie token = ResponseCookie.from(cookieName, value) // key & value
        .httpOnly(true)		// prohibit js reading
        .secure(true)		// also transmit under http
        //.domain(env.getProperty("site.domain"))
        .path("/")			// path
        .maxAge((int)JwtTokenizer2.ACCESS_EXP_TIME)
        .sameSite("Lax")	// In most cases, third-party cookies are not sent, except for Get requests that navigate to the target URL
        .build();

    return token;
  }
  public Cookie getCookie(HttpServletRequest req, String cookieName){
    final Cookie[] cookies = req.getCookies();
    if(cookies==null) return null;
    for(Cookie cookie : cookies){
      if(cookie.getName().equals(cookieName))
        return cookie;
    }
    return null;
  }

  public void deleteCookie(String cookieName) {
    final Cookie cookie = new Cookie(cookieName, null);
    cookie.setMaxAge(0);
  }

  public Boolean chkCookie(HttpServletRequest req, String cookieName){
    final Cookie[] cookies = req.getCookies();
    if(cookies==null) return false;
    for(Cookie cookie : cookies){
      if(cookie.getName().equals(cookieName))
        return true;
    }
    return false;
  }
}