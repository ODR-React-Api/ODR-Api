package com.web.app.domain.Entity;


import java.sql.Date;
import lombok.Data;

@Data
public class MasterPlatforms {

    public String Id;

    public String PlatformName;

    public String Logo;

    public String Logo_Small;

    public String TimeZone;

    public String Currency;

    public String CurrencyMark;

    public double HeatUpWeight;

    public int RemindTiming_Reply;

    public int RemindTiming_Claimreply;

    public int RemindTiming_Negotiation;

    public int RemindTiming_Ex_Neg;

    public int RemindTiming_Mediation;

    public int RemindTiming_Ex_Med;

    public int InfoFrequency;

    public boolean WorkDayBase;

    public int ReplyLimitDays;

    public int CounterclaimLimitDays;

    public int NegotiationLimitDays;

    public int NegotiationExtendDays;

    public int MediationLimitDays;

    public int MediationExtentdDays;

    public int UserProductId;

    public int UseTraderName;

    public int UseProductUrl;

    public int UseOther;

    public int UseRepliesOther;
    public int UseClaimRepliesOther;
    public int UseNegotiationsOther;
    public int UseMediationsOther;

    public int MessageUpdateFrequency;

    public String LanguageId;

    public boolean ShowMiddleName;

    public int MediatorAssignType;

    public boolean MediatorPrivateMessage;

    public boolean MediatorPrivateMessageAddMode;

    public boolean MediatorInfoLimit;

    public int MediatorInfoLimitConfirmDays;

    public boolean Phase_Negotiation;

    public boolean Phase_Mediation;

    public boolean Phase_Arbitration;

    public boolean Phase_Reply;

    public String Other01;

    public String Other02;

    public String Other03;

    public String Other04;

    public String Other05;

    public boolean DeleteFlag;

    public boolean UseDefaultTraderEmail;

    public String DefaultTraderEmail;

    public Date LastModifiedDate;

    public String LastModifiedBy;

    public int MediationChangeableCountSet;

    public int MediationNoReasonChangeableCountSet;

    public String ServiceSiteUrl;

    public int PetitionsContexFile;

    public int RepliesContextFile;

    public int ClaimRepliesContextFileR;

    public int ClaimRepliesContextFileC;

    public int NegotiationsContextFile;

    public int MediationsContextFile;

    public boolean PetitionNoticeMode;

    public String PetitionNoticeEmail;
}
