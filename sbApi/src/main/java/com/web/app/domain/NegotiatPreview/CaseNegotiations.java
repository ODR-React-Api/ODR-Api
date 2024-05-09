package com.web.app.domain.NegotiatPreview;

import java.io.Serializable;

import lombok.Data;

@Data
public class CaseNegotiations implements Serializable{

    private static final long serialVersionUID = 1L;
    
    //
    private String email;

    //ID
    private String id;

    //プラットフォームID
    private String PlatformId;

    //案件ID
    private String CaseId;

    //ステータス
    private String Status;

    //共同編集完了通知フラグ
    private String InfoStatus;

    //希望する解決方法
    private String ExpectResloveTypeValue;

    //その他　内容
    private String OtherContext;

    //和解案　内容
    private String HtmlContext;

    //和解案合意書　内容
    private String HtmlContext2;

    //支払金額
    private String PayAmount;

    //支払期日
    private String PaymentEndDate;

    //返送時送料負担区分
    private String ShipmentPayType;

    //返送時送料負担区分
    private String ShipmentPayValue;

    //特記事項
    private String SpecialItem;

    //提出ユーザ
    private String UserId;

    //和解案提出日
    private String SubmitDate;

    //合意日
    private String AgreementDate;

    //ダウンロードの時の名前
    private String TemplateFileName;

    //反訴の対応方法種類
    private String CounterClaimType;

    //反訴の対応方法「その他」内容
    private String CounterClaimContext;

    //反訴の支払金額
    private String CounterClaimPayment;

    //支払人
    private String PaymentUser;

    //編集可能ユーザ
    private String EditAbleUser;

    //利用言語
    private String LanguageId;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private String DeleteFlag;
    
    private String LastModifiedDate;
    
    private String LastModifiedBy;
}
