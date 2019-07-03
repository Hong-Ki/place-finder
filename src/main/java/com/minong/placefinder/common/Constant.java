package com.minong.placefinder.common;

public class Constant {
  public static final String SUCCESS = "SUCCESS";
  public static final String FAIL = "FAIL";

  public static final long TOKEN_EXP = (1000 * 60 * 100);

  public static final String KAKAO_API_KEY_PREFIX = "KakaoAK ";

  public static final String HEADER_AUTH = "jwt";

  public static final String[] EXCLUDE_PATHS = { "/auth/**" };

}
