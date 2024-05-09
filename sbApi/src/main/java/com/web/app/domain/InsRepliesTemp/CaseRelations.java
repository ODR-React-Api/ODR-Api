package com.web.app.domain.InsRepliesTemp;

import java.sql.Date;

import lombok.Data;

@Data
public class CaseRelations {

    //ID
    public String id;

    //案件ID
    public String caseId;
    
    //申立Id
    public String casePetition;
    
    //プラットフォームID
    public String platformId;
    
    //申立て人
    public String petitionUserId;
    
    //申立て人入力情報
    public String petitionUserInfo_Email;
    
    //代理人1
    public String agent1_Email;
    
    //代理人2
    public String agent2_Email;
    
    //代理人3
    public String agent3_Email;
    
    //代理人4
    public String agent4_Email;
    
    //代理人5
    public String agent5_Email;
    
    //相手方メール
    public String traderUserEmail;
    
    //相手方代理人1
    public String traderAgent1_UserEmail;
    
    //相手方代理人2
    public String traderAgent2_UserEmail;
    
    //相手方代理人3
    public String traderAgent3_UserEmail;
    
    //相手方代理人4
    public String traderAgent4_UserEmail;
    
    //相手方代理人5
    public String traderAgent5_UserEmail;
    
    //調停人
    public String mediatorUserEmail;
    
    ////その他項目
    public String other01;
    
    public String other02;
    
    public String other03;
    
    public String other04;
    
    public String other05;
    
    public boolean deleteFlag;
    
    public Date lastModifiedDate;
    
    public String lastModifiedBy;
}
