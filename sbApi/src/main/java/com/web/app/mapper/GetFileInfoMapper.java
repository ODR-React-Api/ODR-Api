package com.web.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.MosFileList.CaseFileInfo;

/**
 * API_案件添付ファイル取得
 * 
 * @author DUC 閆文静
 * @since 2024/04/25
 * @version 1.0
 */
@Mapper
public interface GetFileInfoMapper {
    // 案件添付ファイル取得
    List<CaseFileInfo> selectCaseFileInfoList(String caseId,String id,Integer positionFlg,Integer mediatorDisclosureFlag);
}
