package com.web.app.service;

import java.util.List;
import com.web.app.domain.couAnswerLogin.RepliesContext;
import com.web.app.domain.couAnswerLogin.InsClaimRepliesDto;

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
    List<RepliesContext> getRepliesContext(String caseId, String platformId);
    //API_反訴への回答データ新規登録
    void insClaimRepliesData(InsClaimRepliesDto insClaimRepliesDto);
}
