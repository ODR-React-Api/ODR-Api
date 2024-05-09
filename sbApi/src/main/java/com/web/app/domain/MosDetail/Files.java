package com.web.app.domain.MosDetail;

import java.io.Serializable;
import lombok.Data;

/**
 * 申立て概要画面
 * API_和解内容取得
 * Dao层
 * Files
 * テーブル名：files添付ファイル
 * 添付資料の取得
 * 
 * @author DUC 張明慧
 * @since 2024/04/25
 * @version 1.0
 */
@Data
public class Files implements Serializable {
    // 添付ファイル
    private String fileName;

    // URL
    private String fileUrl;

    private static final long serialVersionUID = 1L;
}
