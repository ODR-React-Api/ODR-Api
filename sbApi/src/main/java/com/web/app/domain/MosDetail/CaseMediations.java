package com.web.app.domain.MosDetail;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 申立て概要画面
 * API_調停内容取得
 * Dao层
 * CaseMediations
 * テーブル名：case_mediations調停案
 * 調停内容の取得
 * 
 * @author DUC 張明慧
 * @since 2024/04/29
 * @version 1.0
 */
@Data
public class CaseMediations implements Serializable {
    // 案件ID
    private String caseId;

    // ステータス
    private int status;

    // 希望する解決方法
    private String expectResloveTypeValue;

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

    private static final long serialVersionUID = 1L;
}
