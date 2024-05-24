package com.web.app.service;

import com.web.app.domain.MosDetail.PetitionsContent;
import com.web.app.domain.MosDetail.RelationsContent;
import com.web.app.domain.MosDetail.WithdrawalReturn;

/**
 * 申立て概要画面
 * 
 * @author DUC 張万超 王亞テイ
 * @since 2024/4/23
 * @version 1.0
 */
public interface MosDetailService {

    /**
     * ケースの状態を取り下げに変更する。
     *
     * @param caseId 渡し項目.CaseId
     * @param uid    渡し項目.uid
     * @return 変更結果
     */
    WithdrawalReturn applyWithdraw(String caseId, String uid);

    // 申立ての内容取得
    PetitionsContent selectPetitionData(String caseId);

    // 関係者内容取得
    RelationsContent selectRelationsContentData(String caseId);

    // 調停人退出メッセージ登録
    int AddMessages(String caseId, String uid, String platformId, String messageGroupId);

}
