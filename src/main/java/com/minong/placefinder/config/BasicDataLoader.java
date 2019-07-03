package com.minong.placefinder.config;

import com.minong.placefinder.dao.UserDao;
import com.minong.placefinder.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BasicDataLoader {

  private UserDao userDao;

  @Autowired
  public BasicDataLoader(UserDao userDao) {
    this.userDao = userDao;
    LoadUsers();
  }

  private void LoadUsers() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    userDao.save(User.builder().id("test").name("test").password(passwordEncoder.encode("test")).build());
    userDao.save(User.builder().id("user").name("user").password(passwordEncoder.encode("user")).build());
  }
}