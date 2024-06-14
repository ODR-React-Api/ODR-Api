package com.web.app.domain.MedUserChange;

import java.io.Serializable;

import lombok.Data;

@Data
public class UpdMediatorHistoriesChangeable implements Serializable {

    private static final long serialVersionUID = 1L;

    // 案件ID
    private String caseId;

    // 調停人ID
    private String userId;

    // 変更理由
    private String reasonContext;

    // 任意変更理由（選択）
    private int reasonType;

    // 変更希望ユーザータイプ
    private int changeRequestUserType;

    // 変更依頼者UserId
    private String changeRequestUserId;

    private String lastModifiedBy;

    // 理由ありと理由なしのFlag(true:理由あり false:理由なし)
    private boolean withReason;

}
