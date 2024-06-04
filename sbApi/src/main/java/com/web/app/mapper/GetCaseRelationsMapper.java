package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CaseRelations;

/**
 * 関係者メアド取得Mapper
 * 
 * @author DUC 耿浩哲
 * @since 2024/04/25
 * @version 1.0
 */
@Mapper
public interface GetCaseRelationsMapper {

    // 関係者メアド取得
    CaseRelations getCaseRelations(String CaseId);
}
