package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.AnswerLoginConfirm.UpdCasesRelations;

/**
 * S12 回答内容確認画面
 * API_案件別個人情報リレーションデータ更新
 * Mapper層
 * UpdCasesRelationsMapper
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */
@Mapper
public interface UpdCasesRelationsMapper {

    // 代理人更新処理
    int updateCaserelations(UpdCasesRelations Caserelations);
}
