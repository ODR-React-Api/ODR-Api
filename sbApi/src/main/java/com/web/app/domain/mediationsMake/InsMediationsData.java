package com.web.app.domain.mediationsMake;

import java.io.Serializable;

import lombok.Data;

@Data
public class InsMediationsData implements Serializable {
    // 缓冲
    private static final long serialVersionUID = 1L;

    // ID
    private String id;

    // 案件ID
    private String CaseId;

    // プラットフォームID
    private String PlatformId;

    public boolean DeleteFlag;
    
    // 案件種類
    public Integer RelationType;

    // 案件種類ID
    private String RelatedId;

    // 希望する解決方法
    private String ExpectResloveTypeValue;

    // 支払金額
    private double payAmount;

    // 反訴の支払金額
    private double counterClaimPayment;

    // 支払期日
    private String PaymentEndDate;

    // 返送時送料負担区分
    public Integer ShipmentPayType;

    // 特記事項
    private String SpecialItem;

    // 提出ユーザ
    private String UserId;

    // システム日付
    private String LastModifiedDate;

    // ログインユーザ
    private String LastModifiedBy;

}
