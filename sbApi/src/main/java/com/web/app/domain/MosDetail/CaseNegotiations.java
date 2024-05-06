package com.web.app.domain.MosDetail;
import java.util.Date;
import lombok.Data;

/**
 * 申立て概要画面
 * API_和解内容取得
 * Dao层
 * CaseNegotiations 
 * テーブル名：case_negotiations和解案
 * 和解内容の取得
 */
@Data
public class CaseNegotiations {
    // 案件ID
    private String caseId;

    // ステータス
    private int status;

    // 希望する解決方法
    private String expectResloveTypeValue;

    // その他　内容
    private String otherContext;

    // 支払金額
    private double payAmount;

    // 反訴の支払金額
    private double counterClaimPayment;

    // 支払期日
    private Date paymentEndDate;

    // 返送時送料負担区分
    private int shipmentPayType;

    // 特記事項
    private String specialItem;

    // 最終変更日
    private Date lastModifiedDate;
}