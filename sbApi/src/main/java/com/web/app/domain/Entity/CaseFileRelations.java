package com.web.app.domain.Entity;


import java.io.Serializable;
import lombok.Data;

/**
 * 案件-添付ファイルリレーション
 * 
 * @author DUC 馬芹
 * @since 2024/05/06
 * @version 1.0
 */
@Data
public class CaseFileRelations implements Serializable {

    private static final long serialVersionUID = 1L;
    //ID
    private String Id;
    //プラットフォームID
    private String PlatformId;
    //ケース
    private String CaseId;
    //案件種類
    private int RelationType;
    //案件種類ID
    private String RelatedId;
    //ファイルID
    private String FileId;  
    private String Other01;
    private String Other02;
    private String Other03;
    private String Other04;
    private String Other05;
    private int DeleteFlag;
    private String LastModifiedDate;
    private String LastModifiedBy;
}
