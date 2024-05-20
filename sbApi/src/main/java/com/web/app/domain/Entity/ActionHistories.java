package com.web.app.domain.Entity;

import java.util.Date;

import lombok.Data;

@Data
public class ActionHistories  {

    public String id;

    public String PlatformId;

    public String CaseId;

    public String ActionType;

    public Integer CaseStage;

    public String UserId;

    public Integer UserType;

    public Date ActionDateTime;

    public String MessageGroupId;

    public String MessageId;

    public Boolean HaveFile;

    public String Parameters;

    public String Other01;

    public String Other02;

    public String Other03;

    public String Other04;

    public String Other05;

    public Boolean DeleteFlag;

    public Date LastModifiedDate;

    public String LastModifiedBy;
}
