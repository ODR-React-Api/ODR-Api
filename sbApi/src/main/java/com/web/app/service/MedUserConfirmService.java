package com.web.app.service;

import java.util.ArrayList;
import com.web.app.domain.MediateUser;

/**
 * 調停案ステータス取得
 * 調停人メール取得
 * 調停人情報取得
 * 
 * @author DUC jiawenzhi
 * @since 2024/05/10
 * @version 1.0
 */

public interface MedUserConfirmService {
    // 調停案ステータス取得
    String Mediationstatus(String CaseId);

    // 調停人メール取得
    MediateUser GetUserIDbyMail(String CaseId);

    // 調停人情報取得
    ArrayList<MediateUser> MediatorIntelligence(String CaseId);
}
