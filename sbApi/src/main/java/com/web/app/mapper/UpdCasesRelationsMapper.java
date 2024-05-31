package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.AnswerLoginConfirm.UpdCasesRelations;

/**
 * S12 回答内容确认画面
 * API_案件信息更新
 * Mapper层
 * UpdCasesRelationsMapper
 * 
 * @author DUC 李文涛
 * @since 2024/05/30
 * @version 1.0
 */
@Mapper
public interface UpdCasesRelationsMapper {

    // 代理人更新
    int updCasesRelations(UpdCasesRelations Caserelations);
}