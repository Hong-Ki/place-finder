package com.minong.placefinder.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Keyword {
  @Id
  @Column(length = 36)
  private String id = UUID.randomUUID().toString();

  @Column(length = 500, nullable = false, unique = true)
  private String keyword;

  @Column(nullable = false)
  private long count = 1;

  @Builder
  public Keyword(String keyword) {
    this.keyword = keyword;
  }
}