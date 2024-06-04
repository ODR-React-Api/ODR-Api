package com.web.app.domain.AnswerLogin;

import java.io.Serializable;

import lombok.Data;

@Data
public class PetitionDataUser implements Serializable{
    private static final long serialVersionUID = 1L;

    public int userProductId;

    public int useTraderName;

    public int useProductUrl;

    public int useOther;

    public Integer phaseNegotiation;

    public Integer phaseReply;

    public int counterclaimLimitDays;

    public int negotiationLimitDays;

    public int mediationLimitDays;
}
