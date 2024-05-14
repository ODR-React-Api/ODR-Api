package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.Cases;

/**
 * 申立て詳細画面_概要Mapper
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Mapper
public interface UpdCasesStatusMapper {
    // 参加表明対象ケースの状態の判定
    Cases participatedStatusChangeInfoSearch(String caseId);

    // ケースの状態の更新
    int caseStatusChangeUpdate(String caseId);

}
