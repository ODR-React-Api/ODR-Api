package com.web.app.domain.getMediationsData;

import java.io.Serializable;

import lombok.Data;

/**
 * 調停案データ取得API 検索用domain
 * 
 * @author DUC 徐義然
 * @since 2024/05/07
 * @version 1.0
 */
@Data
public class Mediation implements Serializable {
    //バッファリング
    private static final long serialVersionUID = 1L;
    //案件ID
    private String caseId;
    //プラットフォームID
    private String platformId;
    //希望する解決方法
    private String expectResloveTypeValue;
    //ステータス
    private int status;
    //その他　内容
    private String otherContext;
    //支払金額
    private double payAmount;
    //反訴の支払金額
    private double counterClaimPayment;
    //支払期日
    private String paymentEndDate;
    //返送時送料負担区分
    private int shipmentPayType;
    //合意日
    private String agreementDate;
    //特記事項
    private String specialItem;
    //提出ユーザ
    private String userId;
    //ファイル名
    private String fileName;
    //ファイルURL
    private String fileUrl;
}
