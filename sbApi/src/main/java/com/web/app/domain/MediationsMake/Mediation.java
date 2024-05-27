package com.web.app.domain.MediationsMake;

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
    //調停案id
    private String mediationId;
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
    //更新日
    private String lastModifiedDate;
    //提出ユーザ
    private String userId;
    //ファイルid
    private String fileId;
    //ファイル名
    private String fileName;
    //ファイル拡張子
    private String fileExtension;
    //ファイルURL
    private String fileUrl;
    //ファイルサイズ
    private String fileSize;
    //案件-添付ファイルリレーションid
    private String fileRelationId;
    //ファイル削除フラグ
    private int deleteFlag;
}
