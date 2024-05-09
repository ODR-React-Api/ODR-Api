package com.web.app.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DraftSavingDate implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String petitionUserId;

    private String casePetition;

    private String petitionUserInfoEmail;

    private String agent1Email;
    private String agent2Email;
    private String agent3Email;
    private String agent4Email;
    private String agent5Email;

    private String traderUserEmail;

    private String productName;

    private String productId;

    private String traderName;

    private String traderUrl;

    private Date BoughtDate;

    private Double Price;

    private String petitionTypeValue;

    private String petitionContext;

    private String expectResloveTypeValue;
    
    private String other;
}
