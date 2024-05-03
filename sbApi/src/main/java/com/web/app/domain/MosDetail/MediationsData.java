package com.web.app.domain.MosDetail;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 申立て概要画面
 * API_調停内容取得
 * Dao层
 * MediationsData 
 * API「調停内容取得」を呼び出すData
 */
@Data
public class MediationsData implements Serializable {
    // ステータス
    private int status;

    // 対応方法
    private String expectResloveTypeValue;

    // 申し立て支払い金額
    private String payAmount;

    // 反訴支払い金額
    private String counterClaimPayment;

    // 支払期日
    private String paymentEndDate;

    // 返送時送料負担区分
    private int shipmentPayType;

    // 特記事項
    private String specialItem;

    // 最終変更日
    private String lastModifiedDate;

    // 添付資料リスト
    private List<Files> file;

    private static final long serialVersionUID = 1L;

}
