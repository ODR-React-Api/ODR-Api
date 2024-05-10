package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class InsertRepliesData implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String id;
    
    private String PlatformId;

    private String CaseId;

    private int Status;

    private String replyType;

    private String replyContext;

    private String HaveCounterClaim;

    private String CounterClaimContext;

    private String TraderAgent1_UserEmail;

    private String TraderAgent2_UserEmail;

    private String TraderAgent3_UserEmail;

    private String TraderAgent4_UserEmail;

    private String TraderAgent5_UserEmail;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private int DeleteFlag;

    private String LastModifiedDate;

    private String LastModifiedBy;
}
