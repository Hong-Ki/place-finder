package com.minong.placefinder.service;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.minong.placefinder.common.Constant;
import com.minong.placefinder.common.Result;
import com.minong.placefinder.dao.KeywordDao;
import com.minong.placefinder.dao.UserDao;
import com.minong.placefinder.domain.History;
import com.minong.placefinder.domain.Keyword;
import com.minong.placefinder.domain.User;
import com.minong.placefinder.result.PlaceResult;

@Service
public class SearchService {

  @Autowired
  UserDao userDao;
  @Autowired
  KeywordDao keywordDao;

  @Value("${kakao.rest.api.key}")
  String kakaoRestApiKey;
  @Value("${kakao.keyword.search.api.uri}")
  String kakaoKeywordSearchApiUri;
  @Value("${kakao.map.shortcuts.uri}")
  String kakaoMapShortcutsUri;
  @Value("${search.page.column}")
  int searchPageColunm;

  public String getSearchResultForKaKao(Map<String, String> param) {

    Map<String, String> queryParam = new HashMap<>();
    queryParam.put("query", param.get("keyword"));
    queryParam.put("page", param.get("param"));
    URI uri = UriComponentsBuilder.fromUriString(kakaoKeywordSearchApiUri).queryParam("query", param.get("keyword"))
        .queryParam("page", param.get("page")).queryParam("size", searchPageColunm).build().encode().toUri();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", new StringBuilder(Constant.KAKAO_API_KEY_PREFIX).append(kakaoRestApiKey).toString());
    HttpEntity entity = new HttpEntity(headers);

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

    return parsingResult(response.getBody());
  }

  public String parsingResult(String result) {
    try {

      Gson gson = new GsonBuilder().create();
      JsonObject jsonObject = gson.fromJson(result, JsonObject.class);

      List<PlaceResult> list = gson.fromJson(jsonObject.getAsJsonArray("documents"),
          new TypeToken<ArrayList<PlaceResult>>() {
          }.getType());

      for (PlaceResult place : list) {
        place.setShortcuts(new StringBuilder(kakaoMapShortcutsUri).append(place.getId()).toString());
      }

      int total = jsonObject.getAsJsonObject("meta").get("pageable_count").getAsInt();

      Map<String, Object> data = new HashMap<>();
      data.put("places", list);
      data.put("pageTotal",
          total % searchPageColunm == 0 ? total / searchPageColunm : ((int) total / searchPageColunm) + 1);

      return Result.success(data);
    } catch (HttpServerErrorException e) {
      return Result.fail();
    }
  }

  @Transactional
  public String saveHistory(Map<String, String> param) {
    String result = "";

    try {
      User user = userDao.findById(param.get("userId")).orElseThrow(() -> new NoResultException("User doesn't exist"));

      List<History> historyList = user.getHistoryList();

      historyList.sort(Comparator.comparing((History h) -> (h.getDate())));

      historyList.add(0,
          History.builder().keyword(param.get("keyword"))
              .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"))).userId(user.getId())
              .build());

      result = Result.success(historyList);
    } catch (NoResultException e) {
      result = Result.fail(e.getMessage());
    }

    return result;
  }

  @Transactional
  public String saveKeyword(Map<String, String> param) {
    String result = "";

    try {
      Keyword keyword = keywordDao.findOneByKeyword(param.get("keyword"));

      if (keyword != null) {
        keyword.setCount(keyword.getCount() + 1);
      } else {
        keyword = Keyword.builder().keyword(param.get("keyword")).build();
      }
      keywordDao.save(keyword);

      result = Result.success();
    } catch (NoResultException e) {
      result = Result.fail(e.getMessage());
    } catch (NullPointerException e) {
      result = Result.fail(e.getMessage());
    }

    return result;
  }

}