package com.web.app.domain.MosFileList;

import java.io.Serializable;
import lombok.Data;

/**
 * S07_申立て詳細画面・ファイル
 * API_案件添付ファイル一括ダウンロード
 * Dao層
 * BatchDownloadOfCaseFilesParameter
 * API_案件添付ファイル一括ダウンロードの引数
 * 
 * @author DUC 張明慧
 * @since 2024/05/17
 * @version 1.0
 */
@Data
public class BatchDownloadOfCaseFilesParameter implements Serializable {
    // 画面上選択された複数ファイルの「FileUrl」リスト
    private String[] fileUrl;

    private static final long serialVersionUID = 1L;
}
