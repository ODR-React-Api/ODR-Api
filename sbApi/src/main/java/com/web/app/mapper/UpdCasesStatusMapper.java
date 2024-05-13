package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.Cases;

@Mapper
public interface UpdCasesStatusMapper {
    // 参加表明対象ケースの状態の判定
    Cases participatedStatusChangeInfoSearch(String caseId);

    // ケースの状態の更新
    int caseStatusChangeUpdate(String caseId);

}
