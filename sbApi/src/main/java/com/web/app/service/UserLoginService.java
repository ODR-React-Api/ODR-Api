package com.web.app.service;

/**
 * 会員登録フォーム
 * 
 * @author DUC 信召艶
 * @since 2024/04/18
 * @version 1.0
 */
public interface UserLoginService {
    //API_仮会員登録データ取得
    String getPreUserData(String guid);
}
