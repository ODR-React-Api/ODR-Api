package com.web.app.domain.NegotiatPreview;

import java.io.Serializable;
import java.util.ArrayList;

import com.web.app.domain.Entity.File;

import lombok.Data;

@Data
public class NegotiatPreview implements Serializable{

    private static final long serialVersionUID = 1L;

    //和解案ID
    private String id;

    //プラットフォームID
    private String PlatformId;

    //案件ID
    private String CaseId;

    //ステータス
    private Integer Status;

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
    private Integer ShipmentPayType;

    //特記事項
    private String SpecialItem;

    //提出ユーザ
    private String UserId;

    //和解案提出日
    private String SubmitDate;

    //合意日
    private String AgreementDate;

    //反訴の支払金額
    private String CounterClaimPayment;

    //ファイルID
    private String fileId;

    //ファイル名
    private String FileName;

    //拡張子
    private String FileExtension;

    //URL
    private String FileUrl;

    //ストレージID
    private String FileBlobStorageId;

    //ファイルサイズ
    private Integer FileSize;

    //ユーザーID
    private String RegisterUserId;

    //登録日
    private String RegisterDate;

    //Other01
    private String Other01;

    //Other02
    private String Other02;

    //Other03
    private String Other03;

    //Other04
    private String Other04;

    //Other05
    private String Other05;

    //DeleteFlag
    private String DeleteFlag;

    //LastModifiedDate
    private String LastModifiedDate;

    //LastModifiedBy
    private String LastModifiedBy;

    private ArrayList<File> FileList;
}
