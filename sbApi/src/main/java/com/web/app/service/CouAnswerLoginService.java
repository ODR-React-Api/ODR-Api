package com.web.app.service;

import java.util.List;

import com.web.app.domain.CouAnswerLogin.InsClaimRepliesDto;
import com.web.app.domain.CouAnswerLogin.RepliesContext;
import com.web.app.domain.CouAnswerLogin.UpdClaimRepliesDataParameter;

/**
 * S14_反訴回答登録画面
 * Service層
 * CouAnswerLoginService
 * 
 * @author DUC 張明慧 信召艶
 * @since 2024/04/29
 * @version 1.0
 */
public interface CouAnswerLoginService {

    // API_反訴・回答データ取得
    List<RepliesContext> getRepliesContext(String caseId, String platformId);

    // API_反訴への回答データ新規登録
    void insClaimRepliesData(InsClaimRepliesDto insClaimRepliesDto);

    // API_反訴への回答データ更新
    int UpdClaimRepliesData(UpdClaimRepliesDataParameter updClaimRepliesDataParameter);
}
