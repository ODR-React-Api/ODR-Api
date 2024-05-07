package com.web.app.domain.MosDetail;

import lombok.Data;

/**
 * 申立て概要画面
 * API_和解内容取得
 * Dao层
 * Files
 * テーブル名：files添付ファイル
 * 添付資料の取得
 */
@Data
public class Files {
    // 添付ファイル
    private String fileName;

    // URL
    private String fileUrl;
}
