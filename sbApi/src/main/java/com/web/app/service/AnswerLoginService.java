package com.web.app.service;

import com.web.app.domain.AnswerLogin.UpdRepliesDataParameter;

/**
 * S11_回答登録画面
 * Service層
 * AnswerLoginService
 * 
 * @author DUC 張明慧
 * @since 2024/05/14
 * @version 1.0
 */
public interface AnswerLoginService {
    // API_反訴・回答データ新規登録/更新
    int UpdRepliesData(UpdRepliesDataParameter updRepliesDataParameter);
}
