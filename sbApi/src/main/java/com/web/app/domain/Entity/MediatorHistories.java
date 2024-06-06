package com.web.app.domain.Entity;

import lombok.Data;

/**
 * 調停人担当案件履歴
 * 
 * @author DUC 李健
 * @since 2024/06/06
 * @version 1.0
 */
@Data
public class MediatorHistories {
    // ID
    private String id;
    // プラットフォームID
    private String PlatformId;
    // 案件ID
    private String CaseId;
    // 調停人ID
    private String userId;
    private String workflowId;
    // 受理状態
    private String Status;
    // 指名受理状態
    private String AcceptStatus;
    // 変更依頼状態
    private String ChangeRequestStatus;
    // 指名された時間
    private String NominationDateTime;
    // 指名反応時間
    private String NominationResponseTime;
    // 延長回数
    private String ExtensionTimes;
    // 延長回数
    private String ChangeWithoutReason;
    // 無理由変更フラグ
    private String ChangeWithReason;
    // 理由付き変更フラグ
    private String DenyFlag;
    // 辞退フラグ
    private String ResignFlag;
    // 辞任フラグ
    private String ReasonContext;
    // 変更理由
    private String ReasonType;
    // 任意変更理由（選択）
    private String ChangeRequestUserType;

    // 変更希望ユーザータイプ
    private String ChangeRequestDate;

    // 変更希望日
    private String ChangeRequestUserId;

    // 変更依頼者UserId
    private String Other01;
    private String Other02;
    private String Other03;
    private String Other04;
    private String Other05;
    private String DeleteFlag;
    private String LastModifiedDate;
    private String LastModifiedBy;
}
