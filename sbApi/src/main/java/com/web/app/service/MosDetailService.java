package com.web.app.service;

import com.web.app.domain.MosDetail.WithdrawalReturn;

/**
 * 申立て概要画面
 * 
 * @author DUC 張万超
 * @since 2024/4/29
 * @version 1.0
 */
public interface MosDetailService {

    /**
     * ケースの状態を取り下げに変更する。
     *
     * @param caseId 渡し項目.CaseId
     * @param uid 渡し項目.uid
     * @return 変更結果
     */
    WithdrawalReturn applyWithdraw(String caseId, String uid);

}
