package com.web.app.mapper.UserIdentity;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.UserIdentity.UserIdentity;

@Mapper
public interface UserIdentityMapper {

    //根据邮箱查用户是什么身份
    UserIdentity FindUserIdentity(String email);
}
