package com.web.app.domain.Entity;

import java.util.Date;

import lombok.Data;

@Data
public class ActionHistories {

    private String id;

    private String PlatformId;

    private String CaseId;

    private String ActionType;

    private Integer CaseStage;

    private String UserId;

    private Integer UserType;

    private Date ActionDateTime;

    private String MessageGroupId;

    private String MessageId;

    private Boolean HaveFile;

    private String Parameters;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private Boolean DeleteFlag;

    private Date LastModifiedDate;

    private String LastModifiedBy;
}
