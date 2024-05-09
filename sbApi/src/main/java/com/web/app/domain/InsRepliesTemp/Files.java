package com.web.app.domain.InsRepliesTemp;

import java.util.Date;

import lombok.Data;

@Data
public class Files {

    //ID
    public String id;

    //プラットフォームID
    public String platformId;

    //案件ID
    public String caseId;
    
    //ファイル名
    public String fileName;
    
    //拡張子
    public String fileExtension;
    
    //URL
    public String fileUrl;
    
    //ストレージID
    public String fileBlobStorageId;
    
    //ファイルサイズ
    public Integer fileSize;
    
    //ユーザーID
    public String registerUserId;
    
    //登録日
    public Date registerDate;
    
    //その他項目    
    public String other01;
    
    public String other02;
    
    public String other03;
    
    public String other04;
    
    public String other05;
    
    public boolean deleteFlag;
    
    public Date lastModifiedDate;
    
    public String lastModifiedBy;
}
