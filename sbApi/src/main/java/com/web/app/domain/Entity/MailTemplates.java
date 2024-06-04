package com.web.app.domain.Entity;

import lombok.Data;

/**
 * メールテンプレート
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/10
 * @version 1.0
 */
@Data
public class MailTemplates {

    // ID
    private String Id;

    // プラットフォームID
    private String PlatformId;

    // テンプレート番号
    private String TemplateNo;

    private String SendFromMail;

    // 送信元名
    private String SendFromName;

    // タイトル
    private String Subject;

    // HTML内容
    private String HtmlContent;

    // Plain本文内容
    private String TextContent;

    //言語ID
    private String LanguageId;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private Integer DeleteFlag;

    private String LastModifiedDate;

    private String LastModifiedBy;

}
