package com.web.app.domain.Entity;

import java.io.Serializable;
import lombok.Data;
/**
 * 添付ファイル
 * 
 * @author DUC 馬芹
 * @since 2024/05/06
 * @version 1.0
 */
@Data
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;
    //ID
    private String Id;
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
    private int FileSize;
    //ユーザーID
    private String RegisterUserId;
     //登録日
     private String RegisterDate;
    private String Other01;
    private String Other02;
    private String Other03;
    private String Other04;
    private String Other05;
    private int DeleteFlag;
    private String LastModifiedDate;
    private String LastModifiedBy;

}
