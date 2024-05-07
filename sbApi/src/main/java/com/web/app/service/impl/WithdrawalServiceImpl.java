package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.CaseRelations;
import com.web.app.domain.Withdrawal;
import com.web.app.domain.WithdrawalReturn;
import com.web.app.mapper.WithdrawalMapper;
import com.web.app.service.WithdrawalService;

@Service
public class WithdrawalServiceImpl implements WithdrawalService{

  @Autowired
  private WithdrawalMapper withdrawalMapper;

  @Override
  @Transactional
  public WithdrawalReturn withdrawal(String caseId) {
    WithdrawalReturn withdrawalReturn = new WithdrawalReturn();
    Withdrawal withdrawal = withdrawalMapper.getCaseStage(caseId);
    if(withdrawal.getCaseStage() == 0){
      int res = withdrawalMapper.updateWithdrawal(withdrawal.getCid());
      if(res != 0){
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
