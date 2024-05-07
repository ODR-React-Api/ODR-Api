package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.SelectUserInfoForCase;

@Mapper
public interface QueryMapper {

  String getUserInfo(String uid);

  List<SelectUserInfoForCase> selectUserInfoForCase1(String email);

  List<SelectUserInfoForCase> selectUserInfoForCase2(String email);

  List<SelectUserInfoForCase> selectUserInfoForCase3(String email);
  
}
