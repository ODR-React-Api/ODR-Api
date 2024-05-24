package com.web.app.domain.MosDetail;

import java.io.Serializable;
import java.util.List;
import com.web.app.domain.Entity.Files;
import lombok.Data;

/**
 * S04_申立て概要画面
 * API_和解内容取得
 * Dao層
 * GetNegotiationsData
 * API「和解内容取得」を呼び出すData
 * 
 * @author DUC 張明慧
 * @since 2024/04/25
 * @version 1.0
 */
@Data
public class CaseNegotiationsData implements Serializable {
    // ステータス
    private int status;

    // 概要
    private String overview;

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
