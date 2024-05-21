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
    private String ExpectResloveTypeValue;

    // その他 内容
    private String OtherContext;

    // 支払金額
    private double PayAmount;

    // 反訴の支払金額
    private double CounterClaimPayment;

    // 支払期日
    private Date PaymentEndDate;

    // 返送時送料負担区分
    private Integer ShipmentPayType;

    // 特記事項
    private String SpecialItem;

    // 添付ファイル
    private List<AddFile> files;

}
