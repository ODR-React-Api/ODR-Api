package com.web.app.service;

import java.util.ArrayList;

import com.web.app.domain.medUserConfirm.GetUserIDbyMail;

/**
 * 調停案ステータス取得
 * 調停者メールとユザーIDを取得
 * 調停人情報取得
 * 
 * @author DUC 賈文志
 * @since 2024/05/13
 * @version 1.0
 */

public interface MedUserConfirmService {
    // 調停案ステータス取得
    String getMediationStatus(String CaseId);

    // 調停者メールとユザーIDを取得
    GetUserIDbyMail getUserIDbyMail(String CaseId);

    // 調停人情報取得
    ArrayList<GetUserIDbyMail> getMediatorInfo(String CaseId);
}
