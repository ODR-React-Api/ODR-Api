package com.web.app.domain.NegotiatMake;

import java.io.Serializable;
import lombok.Data;

/**
 * 和解案
 * 
 * @author DUC 馬芹
 * @since 2024/05/06
 * @version 1.0
 */
@Data
public class Negotiations implements Serializable {
    private static final long serialVersionUID = 1L;
    // ID
    private String id;
    // 案件ID
    private String caseId;
    // ステータス
    private int status;
    // プラットフォームID
    private String platformId;
    // 希望する解決方法
    private String expectResloveTypeValue;
    // その他 内容
    private String otherContext;
    // 和解案 内容
    private String htmlContext;
    // 和解案合意書 内容
    private String htmlContext2;
    // 支払金額
    private double payAmount;
    // 反訴の支払金額
    private double counterClaimPayment;
    // 支払期日
    private String paymentEndDate;
    // 返送時送料負担区分
    private int shipmentPayType;
    // 特記事項
    private String specialItem;
    // 提出ユーザ
    private String userId;
    // 和解案提出日
    private String submitDate;
    // 合意日
    private String agreementDate;
    private int deleteFlag;
    private String lastModifiedDate;
    private String lastModifiedBy;

}
