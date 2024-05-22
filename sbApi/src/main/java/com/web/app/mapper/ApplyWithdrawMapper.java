package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MosDetail.Withdrawal;

/**
 * API_取り下げ済状態変更
 * 
 * @author DUC 張万超
 * @since 2024/4/29
 * @version 1.0
 */
@Mapper
public interface ApplyWithdrawMapper {

    /**
     * 取り下げ対象ケースの状態の判定
     *
     * @param caseId 画面.CaseId
     * @return cases.cidとcases.CaseStage
     */
    Withdrawal getCaseStage(String caseId);

    /**
     * ケース状態の更新
     *
     * @param caseId 画面.CaseId
     * @return 結果の更新
     */
    int updateWithdrawal(String cid);

}
