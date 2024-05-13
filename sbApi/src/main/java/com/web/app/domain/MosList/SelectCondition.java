package com.web.app.domain.MosList;

import java.io.Serializable;

import lombok.Data;

/**
 * API「検索用ケース詳細取得」の検索条件の引数
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/18
 * @version 1.0
 */
@Data
public class SelectCondition implements Serializable {
    private static final long serialVersionUID = 1L;

    // TBL「案件別個人情報リレーション」.案件ID
    private String caseId;

    // TBL「案件別個人情報リレーション」.申立て人
    private String petitionUserId;

    // 立場フラグ
    private Integer positionFlg;

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
