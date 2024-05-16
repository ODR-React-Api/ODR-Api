package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.MosDetail.Withdrawal;
import com.web.app.domain.MosDetail.WithdrawalReturn;
import com.web.app.mapper.ApplyWithdrawMapper;
import com.web.app.service.MosDetailService;

@Service
public class MosDetailServiceImpl implements MosDetailService{
    
    @Autowired
    private ApplyWithdrawMapper applyWithdrawMapper;

    @Override
    @Transactional
    public WithdrawalReturn applyWithdraw(String caseId) {
        WithdrawalReturn withdrawalReturn = new WithdrawalReturn();
        Withdrawal withdrawal = applyWithdrawMapper.getCaseStage(caseId);
        if (withdrawal.getCaseStage() == 0) {
            int res = applyWithdrawMapper.updateWithdrawal(withdrawal.getCid());
            if (res != 0) {
                withdrawalReturn.setUpdateFlag(0);

                // アクション履歴の登録 TODO

                // メール送信用関係者メアドの取得 TODO
                withdrawalReturn.setCaseRelations(new CaseRelations());

                return withdrawalReturn;
            }
        }

        withdrawalReturn.setUpdateFlag(1);
        return withdrawalReturn;

    }
}
