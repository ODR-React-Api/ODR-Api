package com.web.app.domain;
import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
public class CaseNegotiations  implements Serializable{
    private static final long serialVersionUID = 1L;

    //ID
    private String id;

    //プラットフォームID
    private String PlatformId;

    //案件ID
    private String CaseId;

    //ステータス
    private Integer Status;

    //共同編集完了通知フラグ
    private Integer InfoStatus;

    //希望する解決方法
    private String ExpectResloveTypeValue;

    //その他　内容
    private String OtherContext;

    //和解案　内容
    private String HtmlContext;

    //和解案合意書　内容
    private String HtmlContext2;

    //支払金額
    private double PayAmount;

    //支払期日
    private Date PaymentEndDate;

    //返送時送料負担区分
    private Integer ShipmentPayType;

    //返送時送料負担区分
    private String ShipmentPayValue;

    //特記事項
    private String SpecialItem;

    //提出ユーザ
    private String UserId;

    //和解案提出日
    private Date SubmitDate;

    //合意日
    private Date AgreementDate;

    //ダウンロードの時の名前
    private String TemplateFileName;

    //反訴の対応方法種類
    private String CounterClaimType;

    //反訴の対応方法「その他」内容
    private String CounterClaimContext;

    //反訴の支払金額
    private double CounterClaimPayment;

    //支払人
    private String PaymentUser;

    //編集可能ユーザ
    private String EditAbleUser;

    //利用言語
    private String LanguageId;

    //その他項目
    public String Other01;
    
    public String Other02;
    
    public String Other03;
    
    public String Other04;
    
    public String Other05;
    
    public boolean DeleteFlag;
    
    public Date LastModifiedDate;
    
    public String LastModifiedBy;
}
