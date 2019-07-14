package com.minong.placefinder.controller;

import com.minong.placefinder.service.AuthService;
import com.minong.placefinder.service.KeywordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/keyword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class KeywordController {
  @Autowired
  AuthService authService;
  @Autowired
  KeywordService keywordService;

  @GetMapping(value = "/popular")
  @ResponseBody
  public String getPopular() {
    return keywordService.getPopular();
  }

  @GetMapping(value = "/history")
  @ResponseBody
  public String getHistory(@RequestHeader("jwt") String jwt) {
    String userId = authService.getUserId(jwt);
    return keywordService.getHistory(userId);
  }

}