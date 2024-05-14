package com.web.app.domain.Entity;

import lombok.Data;

/**
 * 案件別個人情報リレーション
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/10
 * @version 1.0
 */
@Data
public class CaseRelations {

    // ID
    public String id;

    // 案件ID
    public String CaseId;
    
    // 申立Id
    public String CasePetition;
    
    // プラットフォームID
    public String PlatformId;
    
    // 申立て人
    public String PetitionUserId;
    
    // 申立て人入力情報
    public String PetitionUserInfo_Email;
    
    // 代理人1
    public String Agent1_Email;
    
    // 代理人2
    public String Agent2_Email;
    
    // 代理人3
    public String Agent3_Email;
    
    // 代理人4
    public String Agent4_Email;
    
    // 代理人5
    public String Agent5_Email;
    
    // 相手方メール
    public String TraderUserEmail;
    
    // 相手方代理人1
    public String TraderAgent1_UserEmail;
    
    // 相手方代理人2
    public String TraderAgent2_UserEmail;
    
    // 相手方代理人3
    public String TraderAgent3_UserEmail;
    
    // 相手方代理人4
    public String TraderAgent4_UserEmail;
    
    // 相手方代理人5
    public String TraderAgent5_UserEmail;
    
    // 調停人
    public String MediatorUserEmail;

    public String ArbitrationUserEmail;
    
    public String Other01;
    
    public String Other02;
    
    public String Other03;
    
    public String Other04;
    
    public String Other05;
    
    public Integer DeleteFlag;
    
    public String LastModifiedDate;
    
    public String LastModifiedBy;
}
