package com.web.app.domain;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import lombok.Data;

@ApiModel 
@Data
public class GetReplies implements Serializable{
    private static final long serialVersionUID = 1L;

    private String replyType;

    private String replyContext;

    private String HaveCounterClaim;

    private String CounterClaimContext;

    private String TraderAgent1_UserEmail;

    private String TraderAgent2_UserEmail;

    private String TraderAgent3_UserEmail;

    private String TraderAgent4_UserEmail;

    private String TraderAgent5_UserEmail;

    private String FileName;

    private String FileUrl;

}

