package com.web.app.domain.Entity;

import lombok.Data;

/**
 * 申立
 * 
 * @author DUC 馮淑慧
 * @since 2024/05/10
 * @version 1.0
 */
@Data
public class CasePetitions {

    // ID
    private String id;

    // プラットフォームID
    private String PlatformId;

    // 案件ID
    private String CaseId;

    // ステータス
    private Integer Status;

    // 商品名
    private String productName;

    // 商品ID
    private String ProductId;

    // 販売元名称
    private String TraderName;

    // 販売元メールアドレス
    private String TraderMail;

    // 販売元ＵＲＬ
    private String TraderUrl;

    // 購入日
    private String BoughtDate;

    // 購入金額
    private Double Price;

    // 申立ての種類
    private String petitionTypeValue;

    // 申立ての種類 その他
    private String petitionTypeOther;

    // 申立て内容
    private String petitionContext;

    // 希望する解決方法
    private String ExpectResloveTypeValue;

    // 希望する解決方法 その他
    private String ExpectResloveTypeOther;

    // その他項目
    private String Other;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private Integer DeleteFlag;

    private String LastModifiedDate;

    private String LastModifiedBy;

    private Integer IsTemp;
}
