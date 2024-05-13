package com.web.app.domain.MosDetail;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * S04_申立て概要画面
 * API_案件状態取得
 * Dao层
 * CasesData
 * テーブル名：cases案件, case_relations案件別個人情報リレーション, case_negotiations和解案,
 * case_mediations調停案
 * 案件のステータスを取得
 * 
 * @author DUC 張明慧
 * @since 2024/04/23
 * @version 1.0
 */
@Data
public class CasesData implements Serializable {
    // ID
    private String cid;

    // 案件ステージ
    private int CaseStage;

    // 案件ステータス
    private String CaseStatus;

    // タイトル名
    private String CaseTitle;

    // 回答期限日
    private Date ReplyEndDate;

    // 反訴の回答期限日
    private Date CounterclaimEndDate;

    // 手続き中止日
    private Date CancelDate;

    // 解決日時
    private Date ResolutionDate;

    // 交渉期限日
    private Date NegotiationEndDate;

    // 調停期限日
    private Date MediationEndDate;

    // 交渉期限日変更ステータス
    private int NegotiationEndDateChangeStatus;

    // 交渉期限日変更回数
    private int NegotiationEndDateChangeCount;

    // 調停期限変更回数
    private int MediationEndDateChangeCount;

    // 個別やりとりステータス（申立人↔調停人）
    private int GroupMessageFlag1;

    // 個別やりとりステータス（相手方↔調停人）
    private int GroupMessageFlag2;

    // 案件別個人情報リレーション.調停人
    private String MediatorUserEmail;

    // 和解案.ステータス
    private String negotiationStatus;

    // 調停案.ステータス
    private String mediationStatus;

    private static final long serialVersionUID = 1L;
}
