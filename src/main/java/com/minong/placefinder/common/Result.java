package com.minong.placefinder.common;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Result {

  public static String success() {
    Map<String, Object> result = new HashMap<>();
    result.put("result", Constant.SUCCESS);

    return new Gson().toJson(result);
  }

  public static String success(Object data) {
    Map<String, Object> result = new HashMap<>();
    result.put("result", Constant.SUCCESS);
    result.put("data", data);

    return new Gson().toJson(result);
  }

  public static String fail() {
    Map<String, Object> result = new HashMap<>();
    result.put("result", Constant.FAIL);

    return new Gson().toJson(result);
  }

  public static String fail(String message) {
    Map<String, Object> result = new HashMap<>();
    result.put("result", Constant.FAIL);
    result.put("message", message);

    return new Gson().toJson(result);
  }
}