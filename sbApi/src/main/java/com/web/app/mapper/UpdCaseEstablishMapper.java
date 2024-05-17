package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.Cases;

/**
 * S20_和解案合意画面
 * Mapper層
 * UpdCaseEstablishMapper
 * API_案件成立更新
 * 
 * @author DUC 馮淑慧
 * @since 2024/05/09
 * @version 1.0
 */
@Mapper
public interface UpdCaseEstablishMapper {

     // 「和解案確認更新API」をコール後、和解案確認データ（StatusとPayAmount）を取得する
     CaseNegotiations selectCaseNegotiations(String caseNegotiationsId);

     // 案件成立更新
     int updateCases(Cases cases);
}
