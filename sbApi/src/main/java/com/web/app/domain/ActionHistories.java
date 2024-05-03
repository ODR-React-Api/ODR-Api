package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class ActionHistories implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id;
   
    private String CaseId;
    
    private String Email; 

    private String PlatformId;

    private String ActionType;

    private Integer CaseStage;

    private String UserId;

    private int UserType;

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

    public boolean HaveFile;

    public boolean DeleteFlag;

    public String LastModifiedBy;
}
