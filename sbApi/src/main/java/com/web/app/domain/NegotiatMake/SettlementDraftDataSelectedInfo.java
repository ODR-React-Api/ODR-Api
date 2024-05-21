package com.web.app.domain.NegotiatMake;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 和解案下書きデータ取得
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Data
public class SettlementDraftDataSelectedInfo implements Serializable {
    // 初期画面表示処理時、和解案下書きデータ取得
    private static final long serialVersionUID = 1L;

    // ID
    private String id;

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
    private int ShipmentPayType;

    // 特記事項
    private String SpecialItem;

    // ステータス
    private int Status;

    // 提出ユーザ
    private String UserId;

    // ファイル名
    private String FileName;

    // URL
    private String FileUrl;

}