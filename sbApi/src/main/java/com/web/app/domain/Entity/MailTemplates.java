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
    public String Id;

    // プラットフォームID
    public String PlatformId;

    // テンプレート番号
    public String TemplateNo;

    public String SendFromMail;

    // 送信元名
    public String SendFromName;

    // タイトル
    public String Subject;

    // HTML内容
    public String HtmlContent;

    // Plain本文内容
    public String TextContent;

    //言語ID
    public String LanguageId;

    public String Other01;

    public String Other02;

    public String Other03;

    public String Other04;

    public String Other05;

    public Integer DeleteFlag;

    public String LastModifiedDate;

    public String LastModifiedBy;

}
