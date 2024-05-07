package com.web.app.domain;

import java.io.Serializable;

//関係者メアド取得
public class ElevantPersonnelEmailAddressInfo implements Serializable {
    // 案件ID
    private String caseId;
    // 申立て人入力情報
    private String petitionUserInfoEmail;
    // 代理人1
    private String agent1Email;
    // 代理人2
    private String agent2Email;
    // 代理人3
    private String agent3Email;
    // 代理人4
    private String agent4Email;
    // 代理人5
    private String agent5Email;
    // 相手方メール
    private String traderUserEmail;
    // 相手方代理人1
    private String traderAgent1UserEmail;
    // 相手方代理人2
    private String traderAgent2UserEmail;
    // 相手方代理人3
    private String traderAgent3UserEmail;
    // 相手方代理人4
    private String traderAgent4UserEmail;
    // 相手方代理人5
    private String traderAgent5UserEmail;

}
