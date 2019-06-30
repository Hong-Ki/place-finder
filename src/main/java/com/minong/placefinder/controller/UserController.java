package com.minong.placefinder.controller;

import java.util.Map;

import com.minong.placefinder.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
  @Autowired
  UserService userService;

  @PostMapping(value = "")
  @ResponseBody
  public String registerUser(@RequestBody Map<String, String> param) {

    return userService.registerUser(param);
  }

  @GetMapping(value = "")
  @ResponseBody
  public String getUser() {
    return userService.getUserList();
  }

}