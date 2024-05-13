package com.web.app.service;

import com.web.app.domain.MosLogin.GetPlatform;

/**
 * S8_申立登録画面
 * Service層
 * MosLoginService
 * API_画面制御表示項目取得
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/23
 * @version 1.0
 */
public interface MosLoginService {

    // 画面制御表示項目取得
    GetPlatform odrUsersSearch(String sessionId);

}
