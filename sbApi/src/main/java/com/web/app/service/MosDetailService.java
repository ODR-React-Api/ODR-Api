package com.web.app.service;

import com.web.app.domain.MosDetail.CaseRepliesMosDetail;

/**
 * S4 申立て詳細画面
 * API_回答の内容取得
 * Service層
 * MosDetailService
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */
public interface MosDetailService {

    // API_回答の内容取得
    CaseRepliesMosDetail getCaseRepliesMosDetail(String caseId);
}
