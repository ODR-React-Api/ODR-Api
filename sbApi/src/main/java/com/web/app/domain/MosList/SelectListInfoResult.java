package com.web.app.domain.MosList;

import java.io.Serializable;
import lombok.Data;

/**
 * 一覧画面表示用のデータ（相手方、申立人、調停人のケース詳細内容）
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/18
 * @version 1.0
 */
@Data
public class SelectListInfoResult implements Serializable {
    private static final long serialVersionUID = 1L;

    // 立場フラグ
    private Integer positionFlg;

    // 申立て番号
    private String cid;

    // 件名
    private String caseTitle;

    // 登録日付
    private String petitionDate;

    // 状態
    private String caseStatus;

    // 対応期日
    private String correspondDate;

    // 未読メッセージ件数
    private Integer msgCount;

    // 要対応有無
    private String correspondence;
}
