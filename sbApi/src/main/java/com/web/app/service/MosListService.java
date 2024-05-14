package com.web.app.service;

import com.web.app.domain.MosList.CaseIdListInfo;
import com.web.app.domain.MosList.ReturnResult;

/**
 * 申立て一覧画面Service
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/17
 * @version 1.0
 */
public interface MosListService {
    // API_ケース詳細取得
    ReturnResult caseDetailCasesInfoSearch(CaseIdListInfo caseIdListInfo);
}
