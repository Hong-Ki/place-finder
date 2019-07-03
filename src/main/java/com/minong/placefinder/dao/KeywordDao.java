package com.minong.placefinder.dao;

import java.util.List;

import com.minong.placefinder.domain.Keyword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordDao extends JpaRepository<Keyword, String> {
  public Keyword findOneByKeyword(String keyword);

  public List<Keyword> findTop10ByOrderByCountDesc();
}
