package com.minong.placefinder.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.minong.placefinder.service.AuthService;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthController {
  @Autowired
  AuthService authService;

  @PostMapping(value = "/login")
  public @ResponseBody String login(@RequestBody Map<String, String> param) {
    System.out.println(param.toString());
    return authService.login(param);
  }

}