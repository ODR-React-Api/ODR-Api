package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.CaseFileInfo;
import com.web.app.domain.GetFileInfo;

@Mapper
public interface GetFileInfoMapper {
    // ログインユーザのロールと開示情報取得
    GetFileInfo selectLoginUserRoleOpenInfo(String caseId);
    // 案件添付ファイル取得
    List<CaseFileInfo> selectCaseFileInfoList(String caseId,String id,Integer positionFlg,Integer mediatorDisclosureFlag);

    
}
