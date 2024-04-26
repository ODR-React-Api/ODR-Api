package com.web.app.service;

import com.github.pagehelper.PageInfo;
import com.web.app.domain.Response;
import com.web.app.domain.User;
import java.util.List;

public interface UserService {
  List<User> findAllUser();

  PageInfo<User> findAllUserByPageHelper(Integer pageNum, Integer pageSize);

  @SuppressWarnings("rawtypes")
  Response addUser(User user);

}
