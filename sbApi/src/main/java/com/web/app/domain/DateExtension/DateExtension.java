package com.web.app.domain.DateExtension;

import java.util.Date;

import lombok.Data;

@Data
public class DateExtension {

    // 案件ID
    private String CaseId;

    // プラットフォームID
    private String PlatformId;

    // 交渉期限日
    private Date NegotiationEndDate;
}
