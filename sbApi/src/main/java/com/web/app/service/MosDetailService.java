package com.web.app.service;

import com.web.app.domain.MosDetail.PetitionsContent;
import com.web.app.domain.MosDetail.RelationsContent;

public interface MosDetailService {

    // 申立ての内容取得
    PetitionsContent selectPetitionData(String caseId);

    // 関係者内容取得
    RelationsContent selectRelationsContentData(String caseId);

    // 調停人退出メッセージ登録
    int updateMediatorHistoriesData(String caseId, String uid, String platformId, String messageGroupId);

}
