package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class UpdRepliesData implements Serializable{
    private static final long serialVersionUID = 1L;
 
    private String replyType;

    private String replyContext;

    private String HaveCounterClaim;

    private String CounterClaimContext;

    private String TraderAgent_UserEmail;

    private String FileName;

    private String FileExtension;

    private String FileUrl;

    private String FileSize;

    private String RelationType;

    
}
