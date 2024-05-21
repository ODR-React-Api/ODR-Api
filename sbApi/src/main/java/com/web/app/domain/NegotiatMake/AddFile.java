package com.web.app.domain.NegotiatMake;

import java.io.Serializable;
import lombok.Data;

/**
 * 下書き保存処理 画面項目から添付ファイル
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Data
public class AddFile implements Serializable {
    // バッファリング
    private static final long serialVersionUID = 1L;

    // ファイルid
    private String fileId;

    // ファイル名
    private String fileName;

    // ファイル拡張子
    private String fileExtension;

    // ファイルURL
    private String fileUrl;

    // ファイルサイズ
    private Integer fileSize;

    // ストレージID
    private String fileBlobStorageId;

    // 削除フラグ
    private int deleteFlag;

}