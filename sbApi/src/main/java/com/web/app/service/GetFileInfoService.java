package com.web.app.service;

import java.util.List;

import com.web.app.domain.CaseFileInfo;
import com.web.app.domain.GetFileInfo;

public interface GetFileInfoService {

    // ログインユーザのロールと開示情報取得
    GetFileInfo getLoginUserRoleOpenInfo(String caseId,String id,String email);
    // 案件添付ファイル取得
    List<CaseFileInfo> getCaseFileInfo(String caseId,String id,Integer positionFlg,Integer mediatorDisclosureFlag);
    
}
