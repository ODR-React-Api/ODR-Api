package com.web.app.service;

import com.web.app.domain.MosFileList.Files;
import com.web.app.domain.MosFileList.LoginUserRoleOpenInfo;

/**
 * S07_申立て詳細画面・ファイル
 * Service層
 * MosFileListService
 * 
 * @author DUC 祭卉康
 * @since 2024/05/20
 * @version 1.0
 */
public interface MosFileListService {

   //ユーザ情報の取得
   LoginUserRoleOpenInfo loginUserRoleOpenInfo(String id, String caseId, String email);
   //添付ファイル情報の取得
   Files files(String id, String caseId);
  
}
