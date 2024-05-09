package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 和解案合意アクション履歴新規登録API
 * 
 * @author DUC jiawenzhi
 * @since 2024/05/09
 * @version 1.0
 */

@Data
public class ActionHistories implements Serializable {
    private static final long serialVersionUID = 1L;
    // 自動生成GIUD
    private String id;
    // 案件ID
    private String CaseId;
    // メールアドレス
    private String Email;
    // プラットフォームID
    private String PlatformId;
    // アクション区分
    private String ActionType;
    // 案件ステージ
    private Integer CaseStage;
    // ログインユーザ
    private String UserId;
    // 立場
    private int UserType;
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
    // ファイル フラグ
    public boolean HaveFile;

    public boolean DeleteFlag;
    // 'ログインユーザ
    public String LastModifiedBy;
}
