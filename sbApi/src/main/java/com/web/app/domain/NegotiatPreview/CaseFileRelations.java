package com.web.app.domain.NegotiatPreview;

import java.io.Serializable;

import lombok.Data;

@Data
public class CaseFileRelations implements Serializable{

    private static final long serialVersionUID = 1L;

    //ID
    private String id;

    //プラットフォームID
    private String PlatformId;

    //ケース
    private String CaseId;

    //案件種類
    private String RelationType;

    //案件種類ID
    private String RelatedId;

    //ファイルID
    private String fileId;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private String DeleteFlag;
    
    private String LastModifiedDate;
    
    private String LastModifiedBy;
}
