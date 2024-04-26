package com.web.app.domain.Entity;

import java.sql.Date;

import lombok.Data;

@Data
public class MailTemplates {
    private String Id;

    private String PlatformId;

    private String TemplateNo;

    private String SendFromMail;

    private String SendFromName;

    private String Subject;
    
    private String HtmlContent;

    private String TextContent;
    
    private String LanguageId;
    
    private String Other01;
    
    private String Other02;
    
    private String Other03;
    
    private String Other04;
    
    private String Other05;
    
    private boolean DeleteFlag;
    
    private Date LastModifiedDate;
    
    private String LastModifiedBy;

}
