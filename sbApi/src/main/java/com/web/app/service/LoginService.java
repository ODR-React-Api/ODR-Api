package com.web.app.service;

import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.Login.LoginUser;

/**
 * C1_ログイン画面
 * Service層
 * LoginService
 * 
 * @author DUC 馮淑慧
 * @since 2024/05/17
 * @version 1.0
 */
public interface LoginService {

    // API_申立データ取得
    OdrUsers LoginUser(LoginUser loginUser);
}
