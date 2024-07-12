package com.together.MunDeuk.web.Common.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;

@Slf4j
public class CommonController {

  public String getCsrfToken(HttpServletRequest request) {

    CsrfToken csrf = (CsrfToken) request.getAttribute("_csrf");
    return csrf.getToken();
  }

}
