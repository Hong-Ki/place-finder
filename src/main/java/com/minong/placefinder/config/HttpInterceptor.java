package com.minong.placefinder.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.minong.placefinder.common.Constant;
import com.minong.placefinder.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class HttpInterceptor implements HandlerInterceptor {

  @Autowired
  private AuthService authService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader(Constant.HEADER_AUTH);
    if (token != null && authService.checkToken(token)) {
      return true;
    } else {
      return false;
    }

  }
}