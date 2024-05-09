package com.web.app.domain.InsRepliesTemp;

import java.util.Date;

import lombok.Data;

@Data
public class CaseExtensionitemValues {

    //ID
    public String id;

    //プラットフォームID
    public String platformId;

    //案件ID
    public String caseId;
    
    //申立てID
    public String case_petitionId;
    
    //拡張項目ID
    public String extensionitemId;
    
    //拡張項目値
    public String extensionitemValue;
 
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
