package com.web.app.domain.Entity;

import java.io.Serializable;

public class OdrUserUtil implements Serializable{
    private static final long serialVersionUID = 1L;

    private String Uid;

    private String Email;

    private String FirstName;

    private String MiddleName;

    private String LastName;

    private String FirstName_Kana;

    private String MiddleName_Kana;

    private String LastName_Kana;

    private String CompanyName;

    private String PlatformId;

    private String LanguageId;

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName_Kana() {
        return FirstName_Kana;
    }

    public void setFirstName_Kana(String firstName_Kana) {
        FirstName_Kana = firstName_Kana;
    }

    public String getMiddleName_Kana() {
        return MiddleName_Kana;
    }

    public void setMiddleName_Kana(String middleName_Kana) {
        MiddleName_Kana = middleName_Kana;
    }

    public String getLastName_Kana() {
        return LastName_Kana;
    }

    public void setLastName_Kana(String lastName_Kana) {
        LastName_Kana = lastName_Kana;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getPlatformId() {
        return PlatformId;
    }

    public void setPlatformId(String platformId) {
        PlatformId = platformId;
    }

    public String getLanguageId() {
        return LanguageId;
    }

    public void setLanguageId(String languageId) {
        LanguageId = languageId;
    }

    

}
