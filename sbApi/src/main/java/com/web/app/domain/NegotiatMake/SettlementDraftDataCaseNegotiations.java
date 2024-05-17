package com.web.app.domain.NegotiatMake;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * テーブル「和解案」が新規登録される
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Data
public class SettlementDraftDataCaseNegotiations implements Serializable {
    private static final long serialVersionUID = 1L;

    // ID
    private String id;
    // プラットフォームID
    private String PlatformId;
    // 案件ID
    private String CaseId;
    // ステータス
    private Integer Status;
    // 希望する解決方法
    private String ExpectResloveTypeValue;
    // その他 内容
    private String OtherContext;
    // 和解案 内容
    private String HtmlContext;
    // 和解案合意書 内容
    private String HtmlContext2;
    // 支払金額
    private double PayAmount;
    // 反訴の支払金額
    private double CounterClaimPayment;
    // 支払期日
    private Date PaymentEndDate;
    // 返送時送料負担区分
    private Integer ShipmentPayType;
    // 特記事項
    private String SpecialItem;
    // 提出ユーザ
    private String UserId;
    // 和解案提出日
    private Date SubmitDate;
    // 合意日
    private Date AgreementDate;

    private Integer DeleteFlag;

    private Date LastModifiedDate;

    private String LastModifiedBy;

}
