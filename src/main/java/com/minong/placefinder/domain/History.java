package com.minong.placefinder.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class History {
  @Id
  @Column(length = 36)
  private String id = UUID.randomUUID().toString();

  @Column(length = 200, nullable = false)
  private String keyword;

  @Column(nullable = false)
  private long time;

  @Column(name = "user_id")
  private String userId;

  @Builder
  public History(String keyword, String userId, long time) {
    this.keyword = keyword;
    this.userId = userId;
    this.time = time;
  }
}