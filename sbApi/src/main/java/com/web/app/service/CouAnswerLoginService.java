package com.web.app.service;

import com.web.app.domain.CouAnswerLogin.UpdClaimRepliesDataParameter;

/**
 * S14_反訴回答登録画面
 * Service層
 * CouAnswerLoginService
 * 
 * @author DUC 張明慧
 * @since 2024/05/02
 * @version 1.0
 */
public interface CouAnswerLoginService {
    // API_反訴への回答データ新規登録/更新
    int UpdClaimRepliesData(UpdClaimRepliesDataParameter updClaimRepliesDataParameter);
}
