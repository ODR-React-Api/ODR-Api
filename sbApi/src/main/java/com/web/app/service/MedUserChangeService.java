package com.web.app.service;

import com.web.app.domain.MedUserChange.InsertFileInfo;

/**
 * 調停人変更画面Service
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/06
 * @version 1.0
 */
public interface MedUserChangeService {

    // 調停案削除
    int delAboutCasesMediations(String caseId);

    // 案件関連情報更新
    int updAboutCasesInfo(String caseId, String userType, Boolean withReason);

    // ファイル関連情報更新
    int insertFileInfo(InsertFileInfo insertFileInfo) throws Exception;

}
