package com.web.app.domain.Entity;

import lombok.Data;

@Data
public class Files {

    // ID
    public String id;

    // プラットフォームID
    public String PlatformId;

    // 案件ID
    public String CaseId;

    // ファイル名
    public String FileName;

    // 拡張子
    public String FileExtension;

    // URL
    public String FileUrl;

    // ストレージID
    public String FileBlobStorageId;

    // ファイルサイズ
    public Integer FileSize;

    // ユーザーID
    public String RegisterUserId;

    // 登録日
    public String RegisterDate;

    // その他項目
    public String Other01;

    public String Other02;

    public String Other03;

    public String Other04;

    public String Other05;

    public Integer DeleteFlag;

    public String LastModifiedDate;

    public String LastModifiedBy;
}
