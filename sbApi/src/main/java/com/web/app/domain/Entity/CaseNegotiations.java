package com.web.app.domain.Entity;

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
public class CaseNegotiations implements Serializable {
    private static final long serialVersionUID = 1L;
    // ID
    private String Id;
    // 案件ID
    private String CaseId;
    // ステータス
    private int Status;
    // プラットフォームID
    private String PlatformId;
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
    private String PaymentEndDate;
    // 返送時送料負担区分
    private int ShipmentPayType;
    // 特記事項
    private String SpecialItem;
    // 提出ユーザ
    private String UserId;
    // 和解案提出日
    private String SubmitDate;
    // 合意日
    private String AgreementDate;
    private int DeleteFlag;
    private String LastModifiedDate;
    private String LastModifiedBy;

}
