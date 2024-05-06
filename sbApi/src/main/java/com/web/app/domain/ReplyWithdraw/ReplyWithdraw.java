package com.web.app.domain.ReplyWithdraw;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ReplyWithdraw implements Serializable {

    private String CaseId;
    
    private String UserId;

    private String PlatformId;

    private Date NegotiationEndDate;

}
