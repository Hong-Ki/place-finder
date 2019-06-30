package com.minong.placefinder.result;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserResult {
  private String name;
  private List<HistoryResult> historyList;
}