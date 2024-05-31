package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 関係者メール
 * 
 * @author DUC 田壮飞
 * @since 2024/05/27
 * @version 1.0
 */
@Data
public class RelatedPersonsEmail implements Serializable{
    private static final long serialVersionUID = 1L;

    // 案件ID
    public String CaseId;

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

}
