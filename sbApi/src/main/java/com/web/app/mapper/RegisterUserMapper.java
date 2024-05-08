package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.UserInsertModel;

@Mapper
public interface RegisterUserMapper {

  Integer userInsert(UserInsertModel userInsert);

  String getMaxUid();
  
}
