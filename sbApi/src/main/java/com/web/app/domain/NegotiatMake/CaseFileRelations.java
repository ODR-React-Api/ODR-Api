package com.web.app.domain.NegotiatMake;


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
    private String id;
    //プラットフォームID
    private String platformId;
    //ケース
    private String caseId;
    //案件種類
    private int relationType;
    //案件種類ID
    private String relatedId;
    //ファイルID
    private String fileId;  
    private String other01;
    private String other02;
    private String other03;
    private String other04;
    private String other05;
    private int deleteFlag;
    private String lastModifiedDate;
    private String lastModifiedBy;
}
