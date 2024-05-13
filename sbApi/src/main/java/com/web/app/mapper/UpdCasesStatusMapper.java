package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.ParticipatedStatusChangeSelectInfo;

@Mapper
public interface UpdCasesStatusMapper {
    // 参加表明対象ケースの状態の判定
    ParticipatedStatusChangeSelectInfo participatedStatusChangeInfoSearch(String caseId);

    // ケースの状態の更新
    int caseStatusChangeUpdate(String caseId);

}
