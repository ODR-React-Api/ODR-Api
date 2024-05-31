package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * API_案件ステータス更新
 * 
 * @author DUC 閆文静
 * @since 2024/05/14
 * @version 1.0
 */
@Mapper
public interface UpdCaseStatusForAcceptMapper {
    // 申立状態を更新
    int updCase(String caseId);
    // 調停人履歴レコードを更新
    int updMediatorHistories(String caseId);  
}
