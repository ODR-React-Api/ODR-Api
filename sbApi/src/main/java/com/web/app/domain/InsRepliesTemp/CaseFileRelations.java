package com.web.app.domain.InsRepliesTemp;

import java.util.Date;

import lombok.Data;

@Data
public class CaseFileRelations {

    //ID
    public String id;

    //プラットフォームID
    public String platformId;

    //案件ID
    public String caseId;
    
    //案件種類
    public String relationType;
    
    //案件種類ID
    public String relatedId;
    
    //ファイルID
    public String fileId;
 
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
