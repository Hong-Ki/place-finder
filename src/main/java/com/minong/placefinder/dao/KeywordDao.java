package com.minong.placefinder.dao;

import com.minong.placefinder.domain.Keyword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordDao extends JpaRepository<Keyword, Long> {
  public Keyword findOneByKeyword(String keyword);
}
