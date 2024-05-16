package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MosDetail.Withdrawal;

@Mapper
public interface ApplyWithdrawMapper {

    Withdrawal getCaseStage(String caseId);

    int updateWithdrawal(String cid);

}
