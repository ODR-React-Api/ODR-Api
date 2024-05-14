package com.web.app.service;

import com.web.app.domain.MosLogin.GetPetitionTemp;
import com.web.app.domain.MosLogin.GetPlatform;
import com.web.app.domain.MosLogin.SessionInfo;

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

    // API_画面制御表示項目取得
    GetPlatform PlatformSearch(String sessionId);

    // API_申立て下書き保存データ取得
    GetPetitionTemp petitionsTempSearch(SessionInfo sessionInfo);

}
