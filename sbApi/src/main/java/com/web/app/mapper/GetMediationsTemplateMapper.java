package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MediationsConCon.MediationsTemplate;

/**
 * S24_調停案内容確認画面
 * Mapper层
 * GetMediationsTemplateMapper
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/12
 * @version 1.0
 */
@Mapper
public interface GetMediationsTemplateMapper {
    // API_調停案テンプレート取得
    List<MediationsTemplate> findMediationsTemplate(String platformId,String languageId);
}
