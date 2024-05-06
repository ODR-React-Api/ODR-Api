package com.web.app.domain;

import lombok.Data;

@Data
public class MasterPlatforms2 {
    public int UserProductId;

    public int UseTraderName;

    public int UseProductUrl;

    public int UseOther;

    public boolean Phase_Negotiation;

    public boolean Phase_Reply;

    public int CounterclaimLimitDays;

    public int NegotiationLimitDays;

    public int MediationLimitDays;
}
