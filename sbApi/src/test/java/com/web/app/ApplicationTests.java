package com.web.app;

import com.github.pagehelper.PageInfo;
import com.web.app.domain.User;
import com.web.app.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTests {

  @Autowired
  private UserService userService;

  @Test
  void contextLoads() {
   List<User> users = userService.findAllUser();
   System.out.println(users);
  }

  @Test
  public void testPageHelper() {
   PageInfo<User> pageInfo = userService.findAllUserByPageHelper(1, 3);
   List<User> list = pageInfo.getList();
   System.out.println(list);
  }

  @Test
  void testTransaction() {
//    User user = new User();
//    user.setName("黄浩恒");
//    userService.addUser(user);
  }

}
