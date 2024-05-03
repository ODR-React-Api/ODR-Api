package com.web.app.service.MosDetail;
import com.web.app.domain.MosDetail.CaseInfo;
import com.web.app.domain.MosDetail.GetCaseInfoParameter;
import com.web.app.domain.MosDetail.MediationsData;
import com.web.app.domain.MosDetail.NegotiationsData;

/**
 * 申立て概要画面
 * Service层
 * MosDetailService
 */
public interface MosDetailService {
    // API_案件状態取得
    CaseInfo getCaseInfo(String caseId, String platformId, String userId);

    // API_チュートリアル表示制御変更
    int updShowTuritor(GetCaseInfoParameter getCaseInfoParameter);

    // API_和解内容取得
    NegotiationsData getNegotiationsData(String caseId);

    // API_調停内容取得
    MediationsData getMediationsData(String caseId);
}
