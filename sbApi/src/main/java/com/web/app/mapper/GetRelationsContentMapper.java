package com.web.app.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.OdrUsers;

@Mapper
public interface GetRelationsContentMapper {

  OdrUsers RelationsContentListDataSearch(String petitionUserInfoEmail);

}
