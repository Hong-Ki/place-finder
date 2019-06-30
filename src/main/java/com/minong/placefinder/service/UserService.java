package com.minong.placefinder.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.minong.placefinder.common.Result;
import com.minong.placefinder.dao.UserDao;
import com.minong.placefinder.domain.User;

@Service
public class UserService {
  @Autowired
  UserDao userDao;

  public String registerUser(Map<String, String> param) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String password = passwordEncoder.encode(param.get("password"));
    System.out.println(param.toString());
    System.out.println(password);
    User user = User.builder().id(param.get("id")).name(param.get("name")).password(password).build();

    String result = Result.success();
    try {
      userDao.save(user);
    } catch (JpaSystemException e) {
      result = Result.fail();
    }

    return result;
  }

  public String getUserList() {
    String result;
    try {
      result = Result.success(userDao.findAll());
    } catch (JpaSystemException e) {
      result = Result.fail();
    }

    return result;
  }
}