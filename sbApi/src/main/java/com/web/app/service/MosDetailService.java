package com.web.app.service;

import com.web.app.domain.MosDetail.PetitionsContent;
import com.web.app.domain.MosDetail.RelationsContent;

/**
 * 申立て詳細画面_概要
 * 
 * @author DUC 王亞テイ
 * @since 2024/4/23
 * @version 1.0
 */
public interface MosDetailService {

    // 申立ての内容取得
    PetitionsContent selectPetitionData(String caseId);

    // 関係者内容取得
    RelationsContent selectRelationsContentData(String caseId);

    // 調停人退出メッセージ登録
    int updateMediatorHistoriesData(String caseId, String uid, String platformId, String messageGroupId);

}
