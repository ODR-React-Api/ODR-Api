package com.web.app.domain.Entity;

import lombok.Data;

/**
 * 送信メール履歴
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/10
 * @version 1.0
 */
@Data
public class MailsHistories {

    // ID
    public String id;

    // プラットフォームID
    public String PlatformId;

    // 案件ID
    public String caseId;

    // ステータス
    public int status;

    // テンプレート番号
    public String TemplateNo;

    // 送信元メールアドレス
    public String FromEmail;

    // 送信者
    public String FromName;

    // 送信先メールアドレス
    public String ToEmail;

    // 受信者
    public String ToName;

    // 送信パラメータ(フォーマット任意)
    public String Parameters;

    // 送信時刻
    public String SendDateTime;

    // 利用言語
    public String LanguageId;

    // リダイレクトURL
    public String RedirectURL;

    // 乱数（GUID?）
    public String UniqueNumber;

    // 開封フラグ
    public Integer OpenFlag;

    // 開封時刻
    public String OpenDate;

    // クリックフラグ
    public Integer LinkFlag;

    // クリック時刻
    public String LinkDate;

    public String M023UniqueNumber;

    public String Other01;

    public String Other02;

    public String Other03;

    public String Other04;

    public String Other05;

    public Integer DeleteFlag;

    public String LastModifiedDate;

    public String LastModifiedBy;

    public String RealEmail;
}
