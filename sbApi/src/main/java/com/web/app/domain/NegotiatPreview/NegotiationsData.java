package com.web.app.domain.NegotiatPreview;

import java.io.Serializable;

import lombok.Data;

@Data
public class NegotiationsData implements Serializable{

    //ログインemail
    private String email;

    //自動生成GIUD
    private String id;

    //PlatformId
    private String PlatformId;

    //CaseId
    private String CaseId;

    //
    private String Status;

    private String InfoStatus;

    private String ExpectResloveTypeValue;

    private String OtherContext;

    private String HtmlContext;

    private String HtmlContext2;

    private String PayAmount;

    private String PaymentEndDate;

    private String ShipmentPayType;

    private String ShipmentPayValue;

    private String SpecialItem;

    private String UserId;

    private String SubmitDate;

    private String AgreementDate;

    private String TemplateFileName;

    private String CounterClaimType;

    private String CounterClaimContext;

    private String CounterClaimPayment;

    private String PaymentUser;

    private String EditAbleUser;

    private String LanguageId;

    // private String Other01;

    // private String Other02;

    // private String Other03;

    // private String Other04;

    // private String Other05;

    // private String DeleteFlag;
    
    // private String LastModifiedDate;
    
    // private String LastModifiedBy;

    private String FileName;
    private String FileExtension;
    private String FileUrl;
    private String FileBlobStorageId;
    private String FileSize;
    private String RegisterUserId;
    private String RegisterDate;


    private String RelationType;
    private String RelatedId;
    private String fileId;
}
