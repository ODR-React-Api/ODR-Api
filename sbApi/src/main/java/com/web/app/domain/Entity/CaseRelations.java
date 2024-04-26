package com.web.app.domain.Entity;

import java.sql.Date;

import lombok.Data;

@Data
public class CaseRelations {

    public String Id;

    public String CaseId;
    
    public String CasePetition;
    
    public String PlatformId;
    
    public String PetitionUserId;
    
    public String PetitionUserInfo_Email;
    
    public String Agent1_Email;
    
    public String Agent2_Email;
    
    public String Agent3_Email;
    
    public String Agent4_Email;
    
    public String Agent5_Email;
    
    public String TraderUserEmail;
    
    public String TraderAgent1_UserEmail;
    
    public String TraderAgent2_UserEmail;
    
    public String TraderAgent3_UserEmail;
    
    public String TraderAgent4_UserEmail;
    
    public String TraderAgent5_UserEmail;
    
    public String MediatorUserEmail;
    
    public String Other01;
    
    public String Other02;
    
    public String Other03;
    
    public String Other04;
    
    public String Other05;
    
    public boolean DeleteFlag;
    
    public Date LastModifiedDate;
    
    public String LastModifiedBy;
}
