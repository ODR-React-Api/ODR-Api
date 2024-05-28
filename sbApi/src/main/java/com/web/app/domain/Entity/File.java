package com.web.app.domain.Entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    //ID
    private String id;

    //プラットフォームID
    private String PlatformId;

    //案件ID
    private String CaseId;

    //ファイル名
    private String FileName;

    //拡張子
    private String FileExtension;

    //URL
    private String FileUrl;

    //ストレージID
    private String FileBlobStorageId;

    //ファイルサイズ
    private Integer FileSize;

    //ユーザーID
    private String RegisterUserId;

    //登録日
    private String RegisterDate;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private Integer DeleteFlag;
    
    private String LastModifiedDate;
    
    private String LastModifiedBy;
}
