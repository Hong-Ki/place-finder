package com.minong.placefinder.controller;

import java.util.HashMap;
import java.util.Map;

import com.minong.placefinder.service.AuthService;
import com.minong.placefinder.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SearchController {
  @Autowired
  SearchService searchService;
  @Autowired
  AuthService authService;

  @GetMapping(value = "")
  public @ResponseBody String search(@RequestParam("keyword") String keyword, @RequestHeader("jwt") String jwt,
      @RequestParam String page) {

    String userId = authService.getUserId(jwt);

    Map<String, String> param = new HashMap<>();
    param.put("keyword", keyword);
    param.put("userId", userId);
    param.put("page", page);

    return searchService.getSearchResultForKaKao(param);

  }

}