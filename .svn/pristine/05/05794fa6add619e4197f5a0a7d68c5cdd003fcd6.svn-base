package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.User;

import java.util.List;

/**
 * @author HHH
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2023-01-31 20:38:15
 * @Entity com.web.app.domain.User
 */
@Mapper
public interface UserMapper {

  List<User> selectAllUser();

  int insertUser(User user);
}
