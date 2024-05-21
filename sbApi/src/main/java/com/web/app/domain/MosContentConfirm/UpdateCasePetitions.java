package com.web.app.domain.MosContentConfirm;

import java.io.Serializable;
import lombok.Data;

@Data
public class UpdateCasePetitions implements Serializable {
    private static final long serialVersionUID = 1L;

    // プラットフォームID
    private String platformId;

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
    private String boughtDate;

    // 購入金額
    private String price;

    // 申立ての種類
    private String petitionTypeValue;

    // 申立て内容
    private String petitionContext;

    // 希望する解決方法
    private String expectResloveTypeValue;

    // その他
    private String other;

    // 言語ID
    private String languageId;
}
