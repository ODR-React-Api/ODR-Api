package com.web.app.domain;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel 
@Data
public class CasePetitions implements Serializable {
  // 案件ID
  private String caseId;

  // 商品名
  private String productName;

  // 商品ID
  private String productId;

  // 販売元名称
  private String traderName;

  // 販売元メールアドレス
  private String traderMail;

  // 販売元ＵＲＬ
  private String traderUrl;

  // 購入日
  private Date boughtDate;

  // 購入金額
  private double price;

  // 申立ての種類
  private String petitionTypeValue;

  // 申立て内容
  private String petitionContext;

  // 希望する解決方法
  private String expectResloveTypeValue;

  // その他項目
  private String other;

  // 最終変更日
  private Date lastModifiedDate;
}