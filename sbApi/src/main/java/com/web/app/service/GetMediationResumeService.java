package com.web.app.service;

import java.util.ArrayList;
import com.web.app.domain.MediateUser;

/**
 * 和解案合意更新API
 * 「アクロン履歴」新規登録
 * メール送信
 * 
 * @author DUC jiawenzhi
 * @since 2024/05/09
 * @version 1.0
 */

public interface GetMediationResumeService {

    MediateUser Mediationstatus(MediateUser mediateUser);

    MediateUser MediationEmail(MediateUser mediateUser);

    ArrayList<MediateUser> MediatorIntelligence(MediateUser mediateUser);
}
