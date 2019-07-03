package com.minong.placefinder.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.minong.placefinder.common.Result;
import com.minong.placefinder.service.AuthService;
import com.minong.placefinder.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthController {
  @Autowired
  AuthService authService;
  @Autowired
  UserService userService;

  @PostMapping(value = "/login")
  public @ResponseBody String login(@RequestParam("id") String id, @RequestParam("password") String password) {
    Map<String, String> param = new HashMap<>();
    param.put("id", id);
    param.put("password", password);
    return authService.login(param);
  }

  @GetMapping(value = "")
  public @ResponseBody String login(@RequestHeader("jwt") String jwt) {
    String result = "";
    boolean isValid = authService.checkToken(jwt);
    String id = authService.getUserId(jwt);

    if (userService.isUser(id) && isValid) {

      result = Result.success();
    } else {
      result = Result.fail();

    }

    return result;
  }

}