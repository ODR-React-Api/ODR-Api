package com.web.app.service;

import com.web.app.domain.MosDetail.CaseInfo;
import com.web.app.domain.MosDetail.UpdShowTuritorParameter;
import com.web.app.domain.MosDetail.CaseMediationsData;
import com.web.app.domain.MosDetail.CaseNegotiationsData;

/**
 * S04_申立て概要画面
 * Service層
 * MosDetailService
 * 
 * @author DUC 張明慧
 * @since 2024/04/22
 * @version 1.0
 */
public interface MosDetailService {
    // API_案件状態取得
    CaseInfo GetCaseInfo(String caseId, String platformId, String userId);

    // API_チュートリアル表示制御変更
    int UpdShowTuritor(UpdShowTuritorParameter updShowTuritorParameter);

    // API_和解内容取得
    CaseNegotiationsData GetCaseNegotiationsData(String caseId);

    // API_調停内容取得
    CaseMediationsData GetCaseMediationsData(String caseId);
}
