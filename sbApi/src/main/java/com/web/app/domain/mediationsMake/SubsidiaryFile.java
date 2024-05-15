package com.web.app.domain.mediationsMake;

import java.io.Serializable;
import lombok.Data;


/**
 * 調停案データ取得API 添付ファイルdomain
 * 
 * @author DUC 徐義然
 * @since 2024/05/07
 * @version 1.0
 */
@Data
public class SubsidiaryFile implements Serializable {
    //バッファリング
    private static final long serialVersionUID = 1L; 
    //ファイルid
    private String fileId;
    //ファイル名
    private String fileName;
    //ファイル拡張子
    private String fileExtension;
    //ファイルURL
    private String fileUrl;
    //ファイルサイズ
    private String fileSize;
    //案件-添付ファイルリレーションid
    private String fileRelationId;
    //削除フラグ
    private int deleteFlag;
}
