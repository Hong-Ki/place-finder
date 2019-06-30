package com.minong.placefinder.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.minong.placefinder.common.Constant;
import com.minong.placefinder.common.Result;
import com.minong.placefinder.dao.UserDao;
import com.minong.placefinder.domain.User;

@Service
public class AuthService {
  @Autowired
  UserDao userDao;

  @Value("${jwt.secret.key}")
  String jwtSecretKey;

  public String login(Map<String, String> param) {
    String result = "";
    try {
      User user = userDao.findById(param.get("id")).orElseThrow(() -> new NoResultException("User doesn't exist"));

      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      boolean isMatch = passwordEncoder.matches(param.get("password"), user.getPassword());

      if (isMatch) {
        String jwt = Jwts.builder().setSubject("user").claim("name", user.getName()).claim("role", "user")
            .setExpiration(new Date(new Date().getTime() + Constant.TOKEN_EXP))
            .signWith(SignatureAlgorithm.HS256, jwtSecretKey.getBytes("UTF-8")).setHeaderParam("typ", "JWT").compact();

        result = Result.success(jwt);

      }
    } catch (UnsupportedEncodingException e) {
      result = Result.fail(e.getMessage());
    } catch (NoResultException e) {
      result = Result.fail(e.getMessage());
    }

    return result;
  }

}