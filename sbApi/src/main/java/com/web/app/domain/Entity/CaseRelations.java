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
    private String id;

    // 案件ID
    private String CaseId;
    
    // 申立Id
    private String CasePetition;
    
    // プラットフォームID
    private String PlatformId;
    
    // 申立て人
    private String PetitionUserId;
    
    // 申立て人入力情報
    private String PetitionUserInfo_Email;
    
    // 代理人1
    private String Agent1_Email;
    
    // 代理人2
    private String Agent2_Email;
    
    // 代理人3
    private String Agent3_Email;
    
    // 代理人4
    private String Agent4_Email;
    
    // 代理人5
    private String Agent5_Email;
    
    // 相手方メール
    private String TraderUserEmail;
    
    // 相手方代理人1
    private String TraderAgent1_UserEmail;
    
    // 相手方代理人2
    private String TraderAgent2_UserEmail;
    
    // 相手方代理人3
    private String TraderAgent3_UserEmail;
    
    // 相手方代理人4
    private String TraderAgent4_UserEmail;
    
    // 相手方代理人5
    private String TraderAgent5_UserEmail;
    
    // 調停人
    private String MediatorUserEmail;

    private String ArbitrationUserEmail;
    
    private String Other01;
    
    private String Other02;
    
    private String Other03;
    
    private String Other04;
    
    private String Other05;
    
    private Integer DeleteFlag;
    
    private String LastModifiedDate;
    
    private String LastModifiedBy;
}
