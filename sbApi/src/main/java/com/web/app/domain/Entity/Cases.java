package com.web.app.domain.Entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Cases {

    public String Cid;

    public String PlatformId;

    public int CaseStage;

    public String CaseStatus;

    public String CaseTitle;

    public Date PetitionDate;

    public Date WithDrawDate;

    public Date ReplyStartDate;

    public Date ReplyEndDate;

    public boolean CounterclaimFlag;

    public Date CounterclaimStartDate;

    public Date CounterclaimEndDate;

    public Date NegotiationStartDate;

    public Date NegotiationEndDate;

    public int NegotiationEndDateChangeStatus;

    public int NegotiationEndDateChangeCount;

    public Date CancelDate;

    public Date MediatorReuqestDate;

    public Date MediationStartDate;

    public Date MediationEndDate;

    public int MediationEndDateChangeCount;

    public Date MediatorNoReasonChangeDate;

    public Date MediatorDisclosureDate;

    public int MediatorChangeableCount1;

    public int MediatorChangeableCount2;

    public int MediatorDisclosureFlag;

    public int GroupMessageFlag1;

    public int GroupMessageFlag2;

    public String GroupMessageComment1;

    public String GroupMessageComment2;

    public Date ArbitrationStartDate;

    public Date ArbitrationEndDate;

    public Date ArbitrationEditDate;

    public Date ResolutionDate;

    public boolean ResolutionFlag;

    public boolean PayFlag;

    public boolean CounterclaimPayFlag;

    public String LanguageId;

    public Date OtherDate01;

    public Date OtherDate02;

    public Date OtherDate03;

    public Date OtherDate04;

    public Date OtherDate05;

    public boolean QuestionnaireSendFlag;

    public String Other01;

    public String Other02;

    public String Other03;

    public String Other04;

    public String Other05;

    public boolean DeleteFlag;

    public Date LastModifiedDate;

    public String LastModifiedBy;

    public int CancelBy;

    public Date CancelActionDate;

    public boolean CancelReuqestBy1;

    public boolean CancelReuqestBy2;

    public boolean CancelReuqestBy3;

    public int MediationChangeableCountSet;

    public int MediationNoReasonChangeableCountSet;

    public boolean JoinedFlag;
}
