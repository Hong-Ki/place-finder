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

  @Column(nullable = false, length = 19)
  private String date;

  @Column(name = "user_id")
  private String userId;

  @Builder
  public History(String keyword, String userId, String date) {
    this.keyword = keyword;
    this.userId = userId;
    this.date = date;
  }
}