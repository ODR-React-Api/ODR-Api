package com.web.app.domain.InsRepliesTemp;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

@Data
public class CasePetitions {

    //ID
    public String id;

    //プラットフォームID
    public String platformId;

    //案件ID
    public String caseId;
    
    //ステータス
    public Integer Status;
    
    //商品名
    public String productName;
    
    //商品ID
    public String productId;
    
    //販売元名称
    public String traderName;
    
    //販売元メールアドレス
    public String traderMail;
    
    //販売元ＵＲＬ
    public String traderUrl;
    
    //購入日
    public Date boughtDate;
    
    //購入金額
    public BigDecimal price;
    
    //申立ての種類
    public String petitionTypeValue;
    
    //申立ての種類　その他
    public String petitionTypeOther;
    
    //申立て内容
    public String petitionContext;
    
    //希望する解決方法
    public String expectResloveTypeValue;
    
    //希望する解決方法　その他
    public String expectResloveTypeOther;
    
    //その他項目
    public String other;
    
    public String other01;
    
    public String other02;
    
    public String other03;
    
    public String other04;
    
    public String other05;
    
    public boolean deleteFlag;
    
    public Date lastModifiedDate;
    
    public String lastModifiedBy;

    public boolean isTemp;
}
