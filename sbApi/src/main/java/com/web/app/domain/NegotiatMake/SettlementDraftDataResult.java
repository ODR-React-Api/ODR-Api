package com.web.app.domain.NegotiatMake;

import java.io.Serializable;
import java.util.List;

import com.web.app.domain.Entity.MasterTypes;

import lombok.Data;

/**
 * 和解案下書き保存処理の返された
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Data
public class SettlementDraftDataResult implements Serializable {
    private static final long serialVersionUID = 1L;

    // 対応方法
    private List<String> correspondence;

    // その他 内容
    private String otherContext;

    // 申立て支払金額
    private double payAmount;

    // 反訴の支払金額
    private double counterClaimPayment;

    // 支払期日
    private String paymentEndDate;

    // 返送時送料
    private int shipmentPayType;

    // 特記事項
    private String specialItem;

    // ステータス
    private int status;

    // ファイル名
    private List<String> fileNameList;

    //返送時送料
    private List<MasterTypes> MasterTypesList;

    // 異常終了利用してのメッセージ
    private String message;

}
