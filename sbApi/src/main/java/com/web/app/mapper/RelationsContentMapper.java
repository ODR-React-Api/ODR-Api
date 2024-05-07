package com.web.app.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Users;

@Mapper
public interface RelationsContentMapper {

  Users RelationsContentListDataSearch(String petitionUserInfoEmail);

}
