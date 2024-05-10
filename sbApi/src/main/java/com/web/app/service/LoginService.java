package com.web.app.service;

import java.util.List;

import com.web.app.domain.LoginUser;

public interface LoginService {
    List<LoginUser> Login(String email, String passWordd);

    boolean updateLoginDate(String email, String passWordd);

    boolean insertActionSuccess(String email, String newId);

    boolean insertActionFail(String email, String newId);

    String selectMaxId();
}
