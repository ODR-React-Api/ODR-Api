package com.web.app.service;

import java.util.List;

import com.web.app.domain.couAnswerLogin.CasesByCid;
import com.web.app.domain.couAnswerLogin.GetRepliesContext;

/**
 * S14_反訴回答登録画面
 * Service層
 * CouAnswerLoginService
 * 
 * @author DUC 信召艶
 * @since 2024/04/29
 * @version 1.0
 */
public interface CouAnswerLoginService {
    //API_反訴・回答データ取得
    List<GetRepliesContext> getRepliesContext(String CaseId,String PlatformId);
    //API_案件データ取得
    List<CasesByCid> casesByCid(String CaseId,String PlatformId);
}
