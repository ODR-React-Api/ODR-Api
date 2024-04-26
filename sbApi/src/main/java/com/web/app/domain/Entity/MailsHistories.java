package com.web.app.domain.Entity;

import java.sql.Date;

import lombok.Data;

@Data
public class MailsHistories {

    public String Id;

    public String PlatformId;

    public String CaseId;

    public int Status;

    public String TemplateNo;

    public String FromEmail;

    public String FromName;

    public String ToEmail;

    public String ToName;

    public String Parameters;

    public Date SendDateTime;

    public String LanguageId;

    public String RedirectURL;

    public String UniqueNumber;

    public boolean OpenFlag;

    public String Other01;

    public String Other02;

    public String Other03;

    public String Other04;

    public String Other05;

    public boolean DeleteFlag;

    public Date LastModifiedDate;

    public String LastModifiedBy;

    public Date OpenDate;

    public boolean LinkFlag;

    public Date LinkDate;

    public String RealEmail;
}
