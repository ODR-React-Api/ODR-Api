package com.web.app.domain.UserInfoConfirm;

import java.io.Serializable;

import lombok.Data;

@Data
public class OdrUserUtil implements Serializable{
    private static final long serialVersionUID = 1L;

    private String Uid;

    private String Email;

    private String password;

    private String FirstName;

    private String MiddleName;

    private String LastName;

    private String FirstName_Kana;

    private String MiddleName_Kana;

    private String LastName_Kana;

    private String CompanyName;

    private String PlatformId;

    private String LanguageId; 

}
