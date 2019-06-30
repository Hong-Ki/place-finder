package com.minong.placefinder.service;

import java.net.URI;
import java.util.Comparator;
import java.util.Date;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.minong.placefinder.common.Constant;
import com.minong.placefinder.common.Result;
import com.minong.placefinder.dao.KeywordDao;
import com.minong.placefinder.dao.UserDao;
import com.minong.placefinder.domain.History;
import com.minong.placefinder.domain.Keyword;
import com.minong.placefinder.domain.User;

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

  public String getSearchResultForKaKao(Map<String, String> param) {

    URI uri = UriComponentsBuilder.fromUriString(kakaoKeywordSearchApiUri).queryParam("query", param.get("keyword"))
        .build().encode().toUri();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", new StringBuilder(Constant.KAKAO_API_KEY_PREFIX).append(kakaoRestApiKey).toString());
    HttpEntity entity = new HttpEntity(headers);

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

    return response.getBody();
  }

  @Transactional
  public String saveHistory(Map<String, String> param) {
    String result = "";

    try {
      User user = userDao.findById(param.get("userId")).orElseThrow(() -> new NoResultException("User doesn't exist"));

      List<History> historyList = user.getHistoryList();

      boolean isFindAndUpdateKeyword = isFindAndUpdateKeyword(historyList, param.get("keyword"));
      historyList.sort(Comparator.comparing((History h) -> (h.getTime() * -1)));

      if (!isFindAndUpdateKeyword) {
        if (historyList.size() >= 5) {
          historyList.remove(historyList.size() - 1);
        }
        historyList.add(0,
            History.builder().keyword(param.get("keyword")).time(new Date().getTime()).userId(user.getId()).build());
      }

      result = Result.success(historyList);
    } catch (NoResultException e) {
      result = Result.fail(e.getMessage());
    }

    return result;
  }

  private boolean isFindAndUpdateKeyword(List<History> list, String keyword) {
    for (History history : list) {
      if (history.getKeyword().equals(keyword)) {
        history.setTime(new Date().getTime());
        return true;
      }
    }

    return false;
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