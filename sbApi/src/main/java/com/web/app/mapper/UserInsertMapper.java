package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.UserInsertModel;

@Mapper
public interface UserInsertMapper {

  Integer userInsert(UserInsertModel userInsert);

  String getMaxUid();
  
}
