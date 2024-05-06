package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.LoginUser;

@Mapper
public interface LoginUserMapper {
    List<LoginUser> Login(String email,String passWord);

    boolean updateLoginDate(String email,String passWord);

    boolean insertActionSuccess(String email, String newId);

    boolean insertActionFail(String email, String newId);

    String selectMaxId();

    
} 
