package com.web.app.service;

import com.web.app.domain.UserInfoConfirm.UserInfoModel;

public interface UserInfoConfirmService {
    public Integer registerUser(UserInfoModel userInfo);
}