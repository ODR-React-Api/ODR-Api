package com.web.app.domain.MosList;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * API_一覧取得に返されるパラメータ
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/17
 * @version 1.0
 */
@Data
public class ReturnResult implements Serializable {
    private static final long serialVersionUID = 1L;

    // 立場フラグ
    private Integer positionFlg;

    // 申立て番号
    private String cid;

    // 件名
    private String caseTitle;

    // 登録日付
    private Date petitionDate;

    // 状態
    private String caseStatus;

    // 対応期日
    private String correspondDate;

    // 未読メッセージ件数
    private Integer msgCount;

    // 要対応有無
    private String correspondence;
}
