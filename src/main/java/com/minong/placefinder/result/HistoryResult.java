package com.minong.placefinder.result;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@NoArgsConstructor
@Getter
@Setter
public class HistoryResult {
  private String keyword;
  private long time;

  @Builder
  public HistoryResult(String keyword, long time) {
    this.keyword = keyword;
    this.time = time;
  }
}