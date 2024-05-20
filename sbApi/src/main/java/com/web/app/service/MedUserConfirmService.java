package com.web.app.service;

import java.util.ArrayList;
import com.web.app.domain.medUserConfirm.GetUserIDbyMail;
import com.web.app.domain.medUserConfirm.GetMediatorInfo;

/**
 * 調停案ステータス取得
 * 調停者メールとユザーIDを取得
 * 調停人情報取得
 * 
 * @author DUC 賈文志
 * @since 2024/05/20
 * @version 1.0
 */
public interface MedUserConfirmService {
    // 調停案ステータス取得
    String getMediationStatus(String CaseId);

    // 調停者メールとユザーIDを取得
    GetUserIDbyMail getUserIDbyMail(String CaseId);

    // 調停人情報取得
    ArrayList<GetMediatorInfo> getMediatorInfo(String CaseId);
}
