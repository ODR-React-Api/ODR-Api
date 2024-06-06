package com.web.app.domain.NamAccept;

import java.util.Date;

import lombok.Data;

/**
 * 調停人変更履歴変更オブジェクト
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/08
 * @version 1.0
 */
@Data
public class UpdMediatorHistories {

    // 調停人ID
    private String userId;

    // 案件ID
    private String caseId;

    // 受理状態
    private String status;

    // システム日付
    private Date lastModifiedDate;

    // ログインユーザ
    private String lastModifiedBy;
}
