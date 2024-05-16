package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.AnswerLoginConfirm.UpdCases;

/**
 * S12 回答内容確認画面
 * API_案件更新
 * Mapper層
 * UpdCasesMapper
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */
@Mapper
public interface UpdCasesMapper {

    //案件状態更新処理
    int updateCasecase(UpdCases Casecase);
}
