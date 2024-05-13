package com.web.app.service;

import java.util.ArrayList;
import com.web.app.domain.MediateUser;

/**
 * 調停案ステータス取得
 * 調停人メール取得
 * 調停人情報取得
 * 
 * @author DUC 賈文志
 * @since 2024/05/13
 * @version 1.0
 */

public interface MedUserConfirmService {
    // 調停案ステータス取得
    String GetMediationStatus(String CaseId);

    // 調停人メール取得
    MediateUser GetUserIDbyMail(String CaseId);

    // 調停人情報取得
    ArrayList<MediateUser> GetMediatorInfo(String CaseId);
}
