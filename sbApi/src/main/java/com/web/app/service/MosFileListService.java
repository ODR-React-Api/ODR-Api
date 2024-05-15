package com.web.app.service;

import java.util.List;
import com.web.app.domain.CaseFileInfo;
import com.web.app.domain.GetFileInfo;

/**
 * S7_申立てファイル一覧画面
 * Service層
 * MosFileListService
 * 
 * @author DUC 閆文静
 * @since 2024/04/25
 * @version 1.0
 */
public interface MosFileListService {

    // ログインユーザのロールと開示情報取得
    GetFileInfo getLoginUserRoleOpenInfo(String caseId, String id, String email);

    // 案件添付ファイル取得
    List<CaseFileInfo> getCaseFileInfo(String caseId, String id, Integer positionFlg, Integer mediatorDisclosureFlag);

}
