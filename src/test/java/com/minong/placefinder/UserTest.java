package com.minong.placefinder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.minong.placefinder.dao.UserDao;
import com.minong.placefinder.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

  @Autowired
  UserDao userDao;

  @Test
  public void testUserRegister() {
    User user = User.builder().id("test1").name("테스트1").password("test1").build();
    userDao.save(user);

    List<User> customerList = userDao.findAll();

    User test = customerList.get(0);
    assertThat(test.getName(), is("테스트1"));
    assertThat(test.getId(), is("test1"));
    assertThat(test.getPassword(), is("test1"));
  }

  @Test
  public void testUserIdCheck() {
    // 중복 테스트용 계정 생성
    User user = User.builder().id("test1").name("테스트1").password("test1").build();
    userDao.save(user);

    boolean isExist = userDao.existsById("test1");

    assertThat(isExist, is(true));

  }

  @After
  public void deleteAll() {
    userDao.deleteAll();
  }

}