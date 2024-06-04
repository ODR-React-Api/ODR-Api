package com.web.app.domain.MosContentConfirm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * case_petitionsの更新項目
 * 
 * @author DUC 王魯興
 * @since 2024/05/29
 * @version 1.0
 */
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
    private Date boughtDate;

    // 購入金額
    private BigDecimal price;

    // 申立ての種類
    private String petitionTypeValue;

    // 申立て内容
    private String petitionContext;

    // 希望する解決方法
    private String expectResloveTypeValue;

    // その他
    private String other;

    // ID
    private String id;
}
