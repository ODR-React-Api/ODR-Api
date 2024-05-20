package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.MosDetail.Withdrawal;
import com.web.app.domain.MosDetail.WithdrawalReturn;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.ApplyWithdrawMapper;
import com.web.app.service.MosDetailService;

/**
 * 申立て概要画面
 * 
 * @author DUC 張万超
 * @since 2024/4/29
 * @version 1.0
 */
@Service
public class MosDetailServiceImpl implements MosDetailService{
    
    @Autowired
    private ApplyWithdrawMapper applyWithdrawMapper;

    /**
     * ケースの状態を取り下げに変更する。
     *
     * @param caseId 渡し項目.CaseId
     * @return 変更結果
     */
    @Override
    @Transactional
    public WithdrawalReturn applyWithdraw(String caseId) {
        // 戻り値オブジェクトの作成
        WithdrawalReturn withdrawalReturn = new WithdrawalReturn();
        // 取り下げ対象ケースの状態の判定
        Withdrawal withdrawal = applyWithdrawMapper.getCaseStage(caseId);
        // 上記取得したCaseStageが0（回答）の場合、以下の処理を実行
        if (withdrawal.getCaseStage() == Constants.STR_CASES_CASESTAGE_0) {
            // ケース状態の更新
            int res = applyWithdrawMapper.updateWithdrawal(withdrawal.getCid());
            if (res != Constants.NUM_0) {
                // 正常に更新の場合、更新済Flgに0（正常更新）を設定して
                withdrawalReturn.setUpdateFlag(Constants.MOSDETAIL_UPDATEFLAG_0);

                // アクション履歴の登録 TODO

                // メール送信用関係者メアドの取得 TODO
                withdrawalReturn.setCaseRelations(new CaseRelations());

                // 上記APIから返された項目を戻り項目として画面へ返す
                return withdrawalReturn;
            }
        }

        // 上記取得したCaseStageが0（回答）以外の場合、以下の処理を実行
        // 正常に更新の場合、更新済Flgに1（更新不可）を設定し、画面へ返す
        withdrawalReturn.setUpdateFlag(Constants.MOSDETAIL_UPDATEFLAG_1);
        return withdrawalReturn;

    }
}
