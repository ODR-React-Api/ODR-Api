package com.web.app.service;

import java.util.HashMap;
import com.web.app.domain.MosLogin.GetPetitionTemp;
import com.web.app.domain.MosLogin.GetPlatform;
import com.web.app.domain.MosLogin.ScreenInfo;
import com.web.app.domain.MosLogin.SessionInfo;

/**
 * S8_申立登録画面
 * Service層
 * MosLoginService
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/23
 * @version 1.0
 */
public interface MosLoginService {

    // API_画面制御表示項目取得
    GetPlatform getPlatform(String sessionId);

    // API_申立て下書き保存データ取得
    GetPetitionTemp getPetitionsTemp(SessionInfo sessionInfo);

    // API_申立て下書きデータ登録
    int insRepliesTemp(ScreenInfo screenInfo);

    // API_販売者・商品情報仮取得
    HashMap<String, String> getGoodsInfo(String goodsId);

}
