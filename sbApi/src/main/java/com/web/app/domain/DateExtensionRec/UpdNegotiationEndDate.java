package com.web.app.domain.DateExtensionRec;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 期日延長申請承認オブジェクト
 * 
 * @author DUC 耿浩哲
 * @since 2024/06/06
 * @version 1.0
 */
@Data
public class UpdNegotiationEndDate implements Serializable {

    private static final long serialVersionUID = 1L;

    // 案件ID
    private String caseId;

    // プラットフォームID
    private String platformId;

    // 交渉期限日
    private Date negotiationEndDate;

    // ログインユーザID
    private String userId;

    // 期日延長申請を承認Flag
    private boolean extensionDateApplicationFlag;
}
