package com.minong.placefinder.controller;

import java.util.HashMap;
import java.util.Map;

import com.minong.placefinder.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SearchController {
  @Autowired
  SearchService searchService;

  @GetMapping(value = "")
  public @ResponseBody String search(@RequestParam("keyword") String keyword, @RequestParam("userId") String userId) {

    Map<String, String> param = new HashMap<>();
    param.put("keyword", keyword);
    param.put("userId", userId);

    searchService.saveHistory(param);
    searchService.saveKeyword(param);

    return searchService.getSearchResultForKaKao(param);

  }

}