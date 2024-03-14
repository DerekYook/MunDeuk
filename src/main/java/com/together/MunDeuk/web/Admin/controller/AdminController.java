package com.together.MunDeuk.web.Admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Validated
@Slf4j
public class AdminController {
  @RequestMapping(value = "/admin/main")
  public String mainPage() {
    return "/web/amng/main";
  }
}
