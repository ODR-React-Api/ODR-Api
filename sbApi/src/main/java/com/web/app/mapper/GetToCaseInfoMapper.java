package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.Cases;
/**
 * S26期日延長画面
 * Mapperr層
 * GetToCaseInfoMapper
 * 
 * @author DUC 田壮飞
 * @since 2024/05/27
 * @version 1.0
 */
@Mapper
public interface GetToCaseInfoMapper {
    //ユーザ情報取得
    Cases FindCaseInfo(String caseId,String platformId);
}
