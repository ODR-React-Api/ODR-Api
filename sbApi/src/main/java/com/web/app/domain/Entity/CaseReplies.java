package com.web.app.domain.Entity;

import java.io.Serializable;
import lombok.Data;

/**
 * CaseReplies
 * テーブル名：case_replies反訴・回答
 * 
 * @author DUC 張明慧
 * @since 2024/05/14
 * @version 1.0
 */
@Data
public class CaseReplies implements Serializable {
    // ID
    public String id;

    // プラットフォームID
    public String PlatformId;

    // 案件ID
    public String CaseId;

    // ステータス
    public Integer Status;

    // 反訴の取り下げ日
    public String WithDrawDate;

    // 申立種類、希望する解決方法種類、回答種類、対応方法の種類など
    public String replyType;

    // 「その他」入力箱テキスト
    public String replyTypeOther;

    // 内容
    public String replyContext;

    // 反訴有無
    public Integer HaveCounterClaim;

    // 反訴内容
    public String CounterClaimContext;

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

    // その他1
    public String Other01;

    // その他2
    public String Other02;

    // その他3
    public String Other03;

    // その他4
    public String Other04;

    // その他5
    public String Other05;

    // 削除Flag
    public Integer DeleteFlag;

    // 最終変更日
    public String LastModifiedDate;

    // 最終変更者
    public String LastModifiedBy;

    private static final long serialVersionUID = 1L;
}
