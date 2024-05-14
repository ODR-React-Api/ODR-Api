package com.web.app.domain.DateExtension;

import java.io.Serializable;
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
public class CaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    // 案件ID
    private String CaseId;

    // プラットフォームID
    private String PlatformId;

    // 交渉期限日
    private Date NegotiationEndDate;
}
