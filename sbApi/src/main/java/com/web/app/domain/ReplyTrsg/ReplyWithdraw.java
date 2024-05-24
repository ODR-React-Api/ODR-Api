package com.web.app.domain.ReplyTrsg;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 反訴取り下げオブジェクト
 * 
 * @author DUC 耿浩哲
 * @since 2024/04/24
 * @version 1.0
 */
@Data
public class ReplyWithdraw implements Serializable {

    private static final long serialVersionUID = 1L;

    // ID
    private String caseId;

    // ユーザID
    private String userId;

    // プラットフォームID
    private String platformId;

    // 交渉期限日
    private Date negotiationEndDate;

}
