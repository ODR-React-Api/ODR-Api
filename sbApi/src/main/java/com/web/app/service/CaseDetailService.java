package com.web.app.service;

import com.web.app.domain.ReturnResult;
import com.web.app.domain.CaseIdListInfo;

/**
 * API_検索用ケース詳細取得
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/17
 * @version 1.0
 */
public interface CaseDetailService {
    ReturnResult CaseDetailCasesInfoSearch(CaseIdListInfo caseIdListInfo);
}
