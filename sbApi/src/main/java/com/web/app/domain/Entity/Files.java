package com.web.app.domain.Entity;

import lombok.Data;

/**
 * 添付ファイル
 * 
 * @author DUC 賈文志
 * @since 2024/05/14
 * @version 1.0
 */
@Data
public class Files {

    // ID
    private String id;

    // プラットフォームID
    private String PlatformId;

    // 案件ID
    private String CaseId;

    // ファイル名
    private String FileName;

    // 拡張子
    private String FileExtension;

    // URL
    private String FileUrl;

    // ストレージID
    private String FileBlobStorageId;

    // ファイルサイズ
    private Integer FileSize;

    // ユーザーID
    private String RegisterUserId;

    // 登録日
    private String RegisterDate;

    // その他項目
    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private Integer DeleteFlag;

    private String LastModifiedDate;

    private String LastModifiedBy;
}