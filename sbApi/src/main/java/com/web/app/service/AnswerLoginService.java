package com.web.app.service;

import java.util.List;

import com.web.app.domain.AnswerLogin.RepliesData;

/**
 * S11_回答登録画面
 * Service層
 * AnswerLoginService
 * 
 * @author DUC 信召艶
 * @since 2024/04/25
 * @version 1.0
 */
public interface AnswerLoginService {
      //API_反訴・回答データ取得
      List<RepliesData> getRepliesData(String caseId, String platformId);
}
