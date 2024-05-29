package com.web.app.domain.NegotiatAgree;

import java.io.Serializable;

import lombok.Data;

/**
 * 和解案確認更新API domain
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/02
 * @version 1.0
 */
@Data
public class UpdNegotiatCon implements Serializable {
    // 缓冲
    private static final long serialVersionUID = 1L;

    // プラットフォームID
    private String negotiationId;

    // ステータス
    private Integer status;

    // 最終更新日
    private String lastModifiedDate;

    // ログインユーザ
    private String loginUser;

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

    // 調停人
    private String mediatorUserEmail;

    // メールアドレス
    private String email;

}
