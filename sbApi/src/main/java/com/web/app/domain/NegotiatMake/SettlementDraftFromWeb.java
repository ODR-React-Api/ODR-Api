package com.web.app.domain.NegotiatMake;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 和解案作成画面の画面項目
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Data
public class SettlementDraftFromWeb implements Serializable {
    private static final long serialVersionUID = 1L;
    // 画面項目から

    // 希望する解決方法
    private List<String> expectResloveTypeValue;

    // その他 内容
    private String otherContext;

    // 支払金額
    private Double payAmount;

    // 反訴の支払金額
    private Double counterClaimPayment;

    // 支払期日
    private Date paymentEndDate;

    // 返送時送料負担区分
    private Integer shipmentPayType;

    // 特記事項
    private String specialItem;

    // 添付ファイル
    private List<AddFile> files;

}
