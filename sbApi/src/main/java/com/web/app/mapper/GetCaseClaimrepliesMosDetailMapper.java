package com.web.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.MosDetail.CaseClaimrepliesMosDetail;

@Mapper
public interface GetCaseClaimrepliesMosDetailMapper {
    
    // API_回答の内容取得:下書き保存データを取得 + 回答・反訴の内容の取得 + 添付資料の取得
    // 下書き保存データを取得 個数
    int selectCaseClaimreplies(String caseId);

    // 反訴への回答内容の取得
    CaseClaimrepliesMosDetail getCaseClaimrepliesMosDetailContent(String caseId);

    // 添付資料の取得
    List<Files> getFiles(String caseId);
}
