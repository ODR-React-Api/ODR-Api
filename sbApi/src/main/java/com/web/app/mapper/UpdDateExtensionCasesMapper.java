package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.DateExtension.CaseInfo;

/**
 * 申立テーブルの内容を更新Mapper
 * 
 * @author DUC 耿浩哲
 * @since 2024/06/06
 * @version 1.0
 */
@Mapper
public interface UpdDateExtensionCasesMapper {

    // 申立テーブルの内容を更新
    int updDateExtensionCases(CaseInfo caseInfo);
}
