package com.web.app.service;

import com.web.app.domain.UserInfoModel;

public interface RegisterUserService {
  int UserInsert(UserInfoModel userInfo);
}
