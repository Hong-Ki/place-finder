package com.minong.placefinder.dao;

import java.util.List;

import com.minong.placefinder.domain.History;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryDao extends JpaRepository<History, String> {
  public long countByUserIdAndKeyword(String userId, String keyword);

  public History findByUserIdAndKeyword(String userId, String keyword);

  public List<History> findAllByUserIdOrderByDateDesc(String userId);
}
