package com.web.app.domain.Entity;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
public class OdrUsers implements Serializable {
    private static final long serialVersionUID = 1L;

    private String Uid;

    private String PlatformId;

    private String Email;

    private String FirstName;

    private String MiddleName;

    private String LastName;

    private String FirstName_EN;

    private String MiddleName_EN;

    private String LastName_EN;

    private String FirstName_Kana;

    private String MiddleName_Kana;

    private String LastName_Kana;

    private String LanguageId;

    private int Status;

    private String TimeZone;

    private String ThemeId;

    private int TermsConfirmStatus;

    private String ResumeFileId;

    private String SelfIntroduce;

    private String HistoryInfo;

    private String Major;

    private String Position;

    private String ProfilePictureFileId;

    private int UserType;

    private String CompanyName;

    private String CompanyName_EN;

    private boolean ShowTuritor1;

    private boolean ShowTuritor2;

    private boolean ShowTuritor3;

    private Date LastLoginDate;

    private Date RegisterDate;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private boolean DeleteFlag;

    private Date LastModifiedDate;

    private String LastModifiedBy;

    private int ConfirmedVersionNoOfTerms;

    private int ConfirmedVersionNoOfPolicy;

    private String NoticeEmail;
    


}
