package com.web.app.service;

import com.web.app.domain.MosFileList.Files;

import com.web.app.domain.MosFileList.UserIdentity;

/**
 * 工具类Service
 * 
 * @author DUC 祭卉康
 * @since 2024/05/20
 * @version 1.0
 */

public interface MosFileListService {

   //用户信息获取
   UserIdentity userIdentity(String id, String caseid, String email);

   /* UserIdentity userIdentity( UserMessage userMessage); */

   //文件信息获取
   Files files(String id, String caseid);
  
}
