package com.web.app.domain.MosContentConfirm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class S09ScreenIntelligence implements Serializable {
    private static final long serialVersionUID = 1L;
    // 画面的入力情报
    // 購入商品
    private String productName;

    // 希望する解決方法
    private String expectResloveTypeValue;

    // 申立人のメールアドレス
    private String etitionUserInfoEmail;

    // 代理人1メールアドレス
    private String agent1Email;

    // 代理人2メールアドレス
    private String agent2Email;

    // 代理人3メールアドレス
    private String agent3Email;

    // 代理人4メールアドレス
    private String agent4Email;

    // 代理人5メールアドレス
    private String agent5Email;

    // 販売者メールアドレス
    private String traderMail;

    // 商品ID
    private String productId;

    // 販売者
    private String traderName;

    // 販売者ＵＲＬ
    private String traderUrl;

    // 購入日
    private Date boughtDate;

    // 購入金額
    private BigDecimal price;

    // 申立ての種類
    private String petitionTypeValue;

    // 申立て内容
    private String petitionContext;

    // その他
    private String other;

    // ファイル名
    private String fileName;

    // FileExtension
    private String fileExtension;

    // FileUrl
    private String fileUrl;

    // FileSize
    private int fileSize;

    //画面上拡張項目
    private List<ExtensionItem> extensionItem;
}
