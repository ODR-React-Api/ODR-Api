package com.web.app.domain.mediationsMake;

import java.io.Serializable;

import com.web.app.domain.Entity.Files;

import lombok.Data;

/**
 * 調停案データ新規登録
 * 
 * @author DUC 賈文志
 * @since 2024/05/20
 * @version 1.0
 */
@Data
public class InsMediationsData implements Serializable {

    // 缓冲
    private static final long serialVersionUID = 1L;
    
    // ログインユーザ
    private String Uid;

    // 案件ID
    private String CaseId;

    // プラットフォームID
    private String PlatformId;

    // 希望する解決方法
    private String ExpectResloveTypeValue;

    // 支払金額
    private double payAmount;

    // 反訴の支払金額
    private double counterClaimPayment;

    // 支払期日
    private String PaymentEndDate;

    // 返送時送料負担区分
    private Integer ShipmentPayType;

    // 特記事項
    private String SpecialItem;

    // システム日付
    private String LastModifiedDate;

    // ログインユーザ
    private String LastModifiedBy;

    // 反訴
    private Integer Counterclaim;

    // システム日付
    private String RegisterDate;

    // 添付ファイル
    private Files insertFiles;

}
