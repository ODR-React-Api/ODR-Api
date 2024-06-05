package com.web.app.domain.getSelectListInfo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 入力パラメータ
 * 
 * @author DUC 郝建润
 * @since 2024/06/04
 * @version 1.0
 */
@ApiModel
@Data
public class MosList implements Serializable {
    // セッション.ユーザID
    private String sessionId;
    // 立場申立人Flg
    private int applicantFlg;
    // 立場相手方Flg
    private int otherFlg;
    // 立場調停人Flg
    private int MediatorFlg;
    // 申立て番号
    private String cid;
    // 件名
    private String caseTitle;
    // 登録日付From
    private String petitionDateForm;
    // 登録日付To
    private String petitionDateTo;
    // ステータス
    private String caseStatus;
    // メッセージ有無Flg
    private String message;
    // 要対応有無Flg
    private String approval;

}
