package com.web.app.domain.DateExtension;

import java.util.Date;

import lombok.Data;

/**
 * 期日延長オブジェクト
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/02
 * @version 1.0
 */
@Data
public class CaseInfo {

    // 案件ID
    private String CaseId;

    // プラットフォームID
    private String PlatformId;

    // 交渉期限日
    private Date NegotiationEndDate;
}
