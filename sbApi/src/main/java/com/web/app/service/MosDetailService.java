package com.web.app.service;

import com.web.app.domain.CaseRelations;
import com.web.app.domain.MosDetail.ParticipatedStatusChangeResultInfo;
import com.web.app.domain.MosDetail.RelationsContent;

/**
 * 申立て詳細画面_概要Service
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/17
 * @version 1.0
 */
public interface MosDetailService {
    // 参加済状態変更
    ParticipatedStatusChangeResultInfo participatedStatusSearch(String caseId, String uId);

    CaseRelations getCaseRelations(String CaseId) throws Exception;

    // 関係者内容取得
    RelationsContent selectRelationsContentData(String caseId);
}
