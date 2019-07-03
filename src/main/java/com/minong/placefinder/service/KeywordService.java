package com.minong.placefinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import com.minong.placefinder.common.Result;
import com.minong.placefinder.dao.KeywordDao;
import com.minong.placefinder.domain.Keyword;

@Service
public class KeywordService {
  @Autowired
  KeywordDao keywordDao;

  public String getKeyword() {
    String result;

    try {
      List<Keyword> list = keywordDao.findTop10ByOrderByCountDesc();

      result = Result.success(list);

    } catch (JpaSystemException e) {
      result = Result.fail();
    }

    return result;
  }
}