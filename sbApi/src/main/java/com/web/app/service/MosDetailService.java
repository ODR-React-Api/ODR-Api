package com.web.app.service;

import com.web.app.domain.MosDetail.CaseInfo;

/**
 * S04_申立て概要画面
 * Service層
 * MosDetailService
 * 
 * @author DUC zzy
 * @since 2024/06/11
 * @version 1.0
 */
public interface MosDetailService {
    // API_案件状態取得
    CaseInfo GetCaseInfo(String caseId, String platformId, String userId);
}
