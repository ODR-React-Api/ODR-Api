package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class NegotiationsEdit implements Serializable {
    private static final long serialVersionUID = 1L;
    public String id;
    public String caseId;
    public int status;
    public String platformId;
    public String expectResloveTypeValue;
    public String otherContext;
    public String htmlContext;
    public String htmlContext2;
    public double payAmount;
    public double counterClaimPayment;
    public String paymentEndDate;
    public int shipmentPayType;
    public String specialItem;
    public String userId;
    public String submitDate;
    public String agreementDate;
    public int deleteFlag;
    public String lastModifiedDate;
    public String lastModifiedBy;

}
