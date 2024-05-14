package com.web.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.CaseFileInfo;
import com.web.app.domain.GetFileInfo;

/**
 * API_ ログインユーザのロールと開示情報取得
 * API_案件添付ファイル取得
 * 
 * @author DUC 閆文静
 * @since 2024/04/25
 * @version 1.0
 */
@Mapper
public interface GetFileInfoMapper {
    // ログインユーザのロールと開示情報取得
    GetFileInfo selectLoginUserRoleOpenInfo(String caseId);
    // 案件添付ファイル取得
    List<CaseFileInfo> selectCaseFileInfoList(String caseId,String id,Integer positionFlg,Integer mediatorDisclosureFlag);

    
}
