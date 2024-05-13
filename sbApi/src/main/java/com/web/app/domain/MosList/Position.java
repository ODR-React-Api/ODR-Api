package com.web.app.domain.MosList;

import java.io.Serializable;

import lombok.Data;

/**
 * 検索サブ画面で入力の画面項目
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/18
 * @version 1.0
 */
@Data
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;

    // セッション.ユーザID
    private String sessionId;

    // 立場申立人Flg
    private int petitionsFlg1;

    // 立場相手方Flg
    private int traderFlg2;

    // 立場調停人Flg
    private int mediatorFlg3;

    // 申立て番号
    private String cid;

    // 件名
    private String caseTitle;

    // 登録日付From
    private String petitionDateStart;

    // 登録日付To
    private String petitionDateEnd;

    // 状態
    private String caseStatus;

    // メッセージ有無Flg
    private String messageFlag;

    // 要対応有無Flg
    private String correspondenceFlag;
}
