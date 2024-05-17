package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.CaseNegotiations;
import com.web.app.domain.Entity.Cases;

@Mapper
public interface UpdCaseEstablishMapper {
     // 「和解案確認更新API」をコール後、和解案確認データ（StatusとPayAmount）を取得する
     CaseNegotiations selectCaseNegotiations(String caseNegotiationsId);

     // 案件成立更新
     int updateCases(Cases cases);
}
