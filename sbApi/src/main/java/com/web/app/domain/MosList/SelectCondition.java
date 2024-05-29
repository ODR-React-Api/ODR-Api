package com.web.app.domain.MosList;

import java.io.Serializable;

import lombok.Data;

@Data
public class SelectCondition implements Serializable {
    private static final long serialVersionUID = 1L;
    // TBL「案件別個人情報リレーション」.案件ID
    private String caseId;
    // TBL「案件別個人情報リレーション」.申立て人
    private String petitionUserId;
    // 立場フラグ
    private int positionFlg;
    // 申立て番号
    private String cid;
    // 件名
    private String caseTitle;
    // 登録日付From
    private String petitionDateStart;
    // 登録日付To
    private String petitionDateEnd;
    // 状態
    private String caseStatus;
}
