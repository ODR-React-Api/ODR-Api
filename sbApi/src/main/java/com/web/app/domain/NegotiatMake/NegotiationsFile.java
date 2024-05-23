package com.web.app.domain.NegotiatMake;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * フロントからの画面項目
 * 
 * @author DUC 馬芹
 * @since 2024/05/06
 * @version 1.0
 */
@Data
public class NegotiationsFile implements Serializable {
    private static final long serialVersionUID = 1L;
    // 希望する解決方法
    private String expectResloveTypeValue;
    // その他　内容
    private String otherContext;
    // 支払金額
    private double payAmount;
    // 反訴の支払金額
    private double counterClaimPayment;
    // 支払期日
    private String paymentEndDate;
    // 返送時送料負担区分
    private int shipmentPayType;
    // 特記事項
    private String specialItem;
    // フロントからのファイル
    private List<UpdNegotiationsFile> updNegotiationsFile;
    
    // セッション情報
    //ログインユーザが申立人場合：1
    //ログインユーザが相手方場合：2
    private int flag;
    //更新時用セッション情報の和解案id
    private String id;
    //ファイルID
    private String filesId;
    //プラットフォームID
    private String platformId;
    //'セッション情報のCaseId
    private String caseId;
    //ログインユーザID
    private String userId;
}
