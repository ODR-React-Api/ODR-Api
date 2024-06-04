package com.web.app.domain.Entity;

import java.io.Serializable;
import lombok.Data;

/**
 * CaseMediations
 * テーブル名：case_mediations調停案
 * 
 * @author DUC 張明慧
 * @since 2024/05/10
 * @version 1.0
 */
@Data
public class CaseMediations implements Serializable {
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

    // 調停案 内容
    private String HtmlContext;

    // 調停合意書 内容
    private String HtmlContext2;

    // 支払金額
    private Double PayAmount;

    // 支払期日
    private String PaymentEndDate;

    // 返送時送料負担区分
    private Integer ShipmentPayType;

    // 返送時送料負担区分
    private String ShipmentPayValue;

    // 特記事項
    private String SpecialItem;

    // 提出ユーザ
    private String UserId;

    // 調停案提出日
    private String SubmitDate;

    // 合意日
    private String AgreementDate;

    // ダウンロードの時の名前
    private String TemplateFileName;

    // 反訴の対応方法種類
    private String CounterClaimType;

    // 反訴の対応方法「その他」内容
    private String CounterClaimContext;

    // 反訴の支払金額
    private Double CounterClaimPayment;

    // 支払人
    private String PaymentUser;

    // 申立人合意ステータス
    private Integer PetitionUserAgreeStatus;

    // 申立人合意日
    private String PetitionUserAgreeDate;

    // 相手方合意ステータス
    private Integer TraderUserAgreeStatus;

    // 相手方合意日
    private String TraderUserAgreeDate;

    // 利用言語
    private String LanguageId;

    // その他1
    public String Other01;

    // その他2
    public String Other02;

    // その他3
    public String Other03;

    // その他4
    public String Other04;

    // その他5
    public String Other05;

    // 削除Flag
    public Integer DeleteFlag;

    // 最終変更日
    public String LastModifiedDate;

    // 最終変更者
    public String LastModifiedBy;

    private static final long serialVersionUID = 1L;
}
