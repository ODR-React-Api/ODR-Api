package com.web.app.domain.Entity;

import java.sql.Date;

import lombok.Data;

@Data
public class MasterTypes {
    public String Id;

    public String PlatformId;

    public String Type;

    public String Value;

    public String DisplayName;

    public int OrderNo;

    public Boolean Required;

    public Boolean IsActive;

    public String LanguageId;

    public String Other01;

    public String Other02;

    public String Other03;

    public String Other04;

    public String Other05;

    public Boolean DeleteFlag;

    public Date LastModifiedDate;

    public String LastModifiedBy;
}
