package com.web.app.service;

import javax.servlet.http.HttpServletResponse;
import com.web.app.domain.Response;
import com.web.app.domain.MosFileList.BatchDownloadOfCaseFilesParameter;

/**
 * S07_申立て詳細画面・ファイル
 * Service層
 * MosFileListService
 * 
 * @author DUC 張明慧
 * @since 2024/05/14
 * @version 1.0
 */
public interface MosFileListService {
    // API_案件添付ファイル一括ダウンロード
    @SuppressWarnings("rawtypes")
    Response BatchDownloadOfCaseFiles(BatchDownloadOfCaseFilesParameter batchDownloadOfCaseFilesParameter,
            HttpServletResponse response);
}
