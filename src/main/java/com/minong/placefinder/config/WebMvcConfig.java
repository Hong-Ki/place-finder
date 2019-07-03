package com.minong.placefinder.config;

import com.minong.placefinder.common.Constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Autowired
  private HttpInterceptor httpInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(httpInterceptor).addPathPatterns("/**").excludePathPatterns(Constant.EXCLUDE_PATHS);
  }
}