package com.web.app.domain.MosList;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class CaseDetailCasesSelectInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    // ID
    private String cId;
    // 案件ステージ
    private Integer caseStage;
    // 案件ステータス
    private String caseStatus;
    // タイトル名
    private String caseTitle;
    // 申立て日
    private Date petitionDate;

    // 対応期日―>start
    // 回答期限日
    private Date replyEndDate;
    // 反訴の回答期限日
    private Date counterclaimEndDate;
    // 交渉期限日
    private Date negotiationEndDate;
    // 調停期限日
    private Date mediationEndDate;
    // ―>対応期日end

    // 交渉期限日変更ステータス
    private Integer negotiationEndDateChangeStatus;
    // 個別やりとりステータス（申立人↔調停人）
    private Integer groupMessageFlag1;
    // 個別やりとりステータス（相手方↔調停人）
    private Integer groupMessageFlag2;
    // 調停人情報開示フラグ
    private Integer mediatorDisclosureFlag;
    // case_negotiationsステータス
    private Integer caseNegotiationsStatus;
    // case_mediations Status
    private Integer mediationsStatus;

}
