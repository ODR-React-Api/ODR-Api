package com.web.app.domain.MosDetail;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 申立て概要画面
 * API_案件状態取得
 * Dao层
 * Cases 
 * テーブル名：cases案件, case_relations案件別個人情報リレーション, case_negotiations和解案, case_mediations調停案
 * 案件のステータスを取得
 */
@Data
public class Cases implements Serializable {
    // ID
    private String cid;
    // 案件ステージ
    private int caseStage;
    // 案件ステータス
    private String caseStatus;
    // タイトル名
    private String caseTitle;
    // 回答期限日
    private Date replyEndDate;
    // 反訴の回答期限日
    private Date counterclaimEndDate;
    // 手続き中止日
    private Date cancelDate;
    // 解決日時
    private Date resolutionDate;
    // 交渉期限日
    private Date negotiationEndDate;
    // 調停期限日
    private Date mediationEndDate;
    // 交渉期限日変更ステータス
    private int negotiationEndDateChangeStatus;
    // 交渉期限日変更回数
    private int negotiationEndDateChangeCount;
    // 調停期限変更回数
    private int mediationEndDateChangeCount ;
    // 個別やりとりステータス（申立人↔調停人）
    private int groupMessageFlag1 ;
    // 個別やりとりステータス（相手方↔調停人）
    private int groupMessageFlag2;
    // 案件別個人情報リレーション.調停人
    private String mediatorUserEmail;
    // 和解案.ステータス
    private String negotiationStatus;
    // 調停案.ステータス
    private String mediationStatus;

    private static final long serialVersionUID = 1L;
}
