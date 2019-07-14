package com.minong.placefinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import com.minong.placefinder.common.Result;
import com.minong.placefinder.dao.HistoryDao;
import com.minong.placefinder.dao.KeywordDao;
import com.minong.placefinder.dao.UserDao;
import com.minong.placefinder.domain.Keyword;
import com.minong.placefinder.domain.User;

@Service
public class KeywordService {
  @Autowired
  KeywordDao keywordDao;

  @Autowired
  UserDao userDao;
  @Autowired
  HistoryDao historyDao;

  public String getPopular() {
    String result;

    try {
      List<Keyword> list = keywordDao.findTop10ByOrderByCountDesc();

      result = Result.success(list);

    } catch (JpaSystemException e) {
      result = Result.fail();
    }

    return result;
  }

  public String getHistory(String userId) {
    String result;

    try {
      result = Result.success(historyDao.findAllByUserIdOrderByDateDesc(userId));
    } catch (JpaSystemException e) {
      result = Result.fail();
    }

    return result;
  }
}