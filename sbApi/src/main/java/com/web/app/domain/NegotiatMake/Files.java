package com.web.app.domain.NegotiatMake;

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
    private String id;
    //プラットフォームID
    private String platformId;
    //案件ID
    private String caseId;
    //ファイル名
    private String fileName;
    //拡張子
    private String fileExtension;
    //URL
    private String fileUrl;
    //ストレージID
    private String fileBlobStorageId;
    //ファイルサイズ
    private int fileSize;
    //ユーザーID
    private String registerUserId;
    //登録日
    private String registerDate;
    private String other01;
    private String other02;
    private String other03;
    private String other04;
    private String other05;
    private int deleteFlag;
    private String lastModifiedDate;
    private String lastModifiedBy;

}
