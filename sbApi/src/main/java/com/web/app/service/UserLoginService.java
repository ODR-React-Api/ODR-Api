package com.web.app.service;

import com.web.app.domain.userLogin.GetPreUserData;
import java.util.List;

/**
 * API_仮会員登録データ取得
 * 
 * @author DUC 信召艶
 * @since 2024/04/18
 * @version 1.0
 */
public interface UserLoginService {

    // guid取得
    List<GetPreUserData> getUserPre(String guid);
}
