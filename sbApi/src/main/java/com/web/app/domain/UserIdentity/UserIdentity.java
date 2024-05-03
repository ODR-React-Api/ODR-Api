package com.web.app.domain.UserIdentity;

import lombok.Data;

@Data
public class UserIdentity {

    //申立て人メール
    private String PetitionUserInfo_Email;

    //申立て人代理人1
    private String Agent1_Email;

    //申立て人代理人2
    private String Agent2_Email;

    //申立て人代理人3
    private String Agent3_Email;

    //申立て人代理人4
    private String Agent4_Email;

    //申立て人代理人5
    private String Agent5_Email;

    //相手方メール
    private String TraderUserEmail;

    //相手方代理人1
    private String TraderAgent1_UserEmail;

    //相手方代理人2
    private String TraderAgent2_UserEmail;

    //相手方代理人3
    private String TraderAgent3_UserEmail;

    //相手方代理人4
    private String TraderAgent4_UserEmail;

    //相手方代理人5
    private String TraderAgent5_UserEmail;

    //調停人
    private String MediatorUserEmail;
}
