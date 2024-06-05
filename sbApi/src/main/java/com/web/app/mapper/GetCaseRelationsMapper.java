package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CaseRelations;

/**
 * 関係者メアド取得
 * Mapperr層
 * GetCaseRelationsMapper
 * 
 * @author DUC 田壮飞
 * @since 2024/05/27
 * @version 1.0
 */
@Mapper
public interface GetCaseRelationsMapper {
    
    // 関係者のメールアドレスを取得する
    CaseRelations findCaseRelations(String caseId);

}
