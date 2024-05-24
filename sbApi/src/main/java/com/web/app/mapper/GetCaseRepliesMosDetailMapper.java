package com.web.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.MosDetail.CaseRepliesMosDetail;

/**
 * S4 申立て詳細画面
 * API_回答の内容取得
 * Mapper層
 * GetCaseRepliesMosDetailMapper
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */

@Mapper
public interface GetCaseRepliesMosDetailMapper {

    // API_回答の内容取得:下書き保存データを取得 + 回答・反訴の内容の取得 + 添付資料の取得
    // 下書き保存データを取得 個数
    int selectCasereplies(String caseId);

    // 回答・反訴の内容の取得
    CaseRepliesMosDetail getCaserepliesAnswerContent(String caseId);

    // 添付資料の取得
    List<Files> getFiles(String caseId);

}
