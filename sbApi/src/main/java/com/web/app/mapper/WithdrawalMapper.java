package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Withdrawal;

@Mapper
public interface WithdrawalMapper {

  Withdrawal getCaseStage(String caseId);

  int updateWithdrawal(String cid);
  
}
