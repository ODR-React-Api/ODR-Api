package com.web.app.service;

import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.MosDetail.CaseClaimrepliesMosDetail;
import com.web.app.domain.MosDetail.CaseInfo;
import com.web.app.domain.MosDetail.CaseMediationsData;
import com.web.app.domain.MosDetail.CaseNegotiationsData;
import com.web.app.domain.MosDetail.CaseRepliesMosDetail;
import com.web.app.domain.MosDetail.ParticipatedStatusChangeResultInfo;
import com.web.app.domain.MosDetail.PetitionsContent;
import com.web.app.domain.MosDetail.RelationsContent;
import com.web.app.domain.MosDetail.UpdShowTuritorParameter;
import com.web.app.domain.MosDetail.WithdrawalReturn;

/**
 * S04_申立て概要画面
 * Service層
 * MosDetailService
 * 
 * @author DUC 張明慧 楊バイバイ耿浩哲 朱暁芳 張万超 王亞テイ
 * @since 2024/04/17
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

    // 参加済状態変更
    ParticipatedStatusChangeResultInfo participatedStatusSearch(String caseId, String uId);

    // 関係者メアド取得
    CaseRelations getCaseRelations(String caseId) throws Exception;

    // API_回答の内容取得
    CaseRepliesMosDetail getCaseRepliesMosDetail(String caseId);

    // API_反訴への回答取得
    CaseClaimrepliesMosDetail getCaseClaimrepliesMosDetail(String caseId);

    // API_案件状態取得
    CaseInfo GetCaseInfo(String caseId, String platformId, String userId);

    // API_チュートリアル表示制御変更
    int UpdShowTuritor(UpdShowTuritorParameter updShowTuritorParameter);

    // API_和解内容取得
    CaseNegotiationsData GetCaseNegotiationsData(String caseId);

    // API_調停内容取得
    CaseMediationsData GetCaseMediationsData(String caseId);
}
