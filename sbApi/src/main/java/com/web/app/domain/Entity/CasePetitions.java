package com.web.app.domain.Entity;

import lombok.Data;

@Data
public class CasePetitions {

    // ID
    public String id;

    // プラットフォームID
    public String PlatformId;

    // 案件ID
    public String CaseId;

    // ステータス
    public Integer Status;

    // 商品名
    public String productName;

    // 商品ID
    public String ProductId;

    // 販売元名称
    public String TraderName;

    // 販売元メールアドレス
    public String TraderMail;

    // 販売元ＵＲＬ
    public String TraderUrl;

    // 購入日
    public String BoughtDate;

    // 購入金額
    public Double Price;

    // 申立ての種類
    public String petitionTypeValue;

    // 申立ての種類 その他
    public String petitionTypeOther;

    // 申立て内容
    public String petitionContext;

    // 希望する解決方法
    public String ExpectResloveTypeValue;

    // 希望する解決方法 その他
    public String ExpectResloveTypeOther;

    // その他項目
    public String Other;

    public String Other01;

    public String Other02;

    public String Other03;

    public String Other04;

    public String Other05;

    public Integer DeleteFlag;

    public String LastModifiedDate;

    public String LastModifiedBy;

    public Integer IsTemp;
}
