package com.web.app.service;

import com.web.app.domain.answerLogin.GetReplies;
import java.util.List;

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
      List<GetReplies> getReplies(String CaseId,String PlatformId);
}
