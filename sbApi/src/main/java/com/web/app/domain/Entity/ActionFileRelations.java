package com.web.app.domain.Entity;

import java.util.Date;

import lombok.Data;

@Data
public class ActionFileRelations {
    private String Id;

    private String PlatformId;

    private String CaseId;

    private String ActionHistoryId;

    private String FileId;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private boolean DeleteFlag;

    private Date LastModifiedDate;

    private String LastModifiedBy;
}
