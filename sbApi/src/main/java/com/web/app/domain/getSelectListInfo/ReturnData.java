package com.web.app.domain.getSelectListInfo;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

/**
 * 戻った値で２次元配列（もしくはリスト）を設定する。
 * 立場フラグ：
 * 申立て番号：
 * 件名：
 * 登録日付：
 * 状態：
 * 対応期日：
 * 未読メッセージ件数：
 * 要対応有無：
 * 
 * @author DUC 郝建润
 * @since 2024/06/04
 * @version 1.0
 */
@Data
public class ReturnData implements Serializable {
    private static final long serialVersionUID = 1L;
    // 立場フラグ：1（申立人）
    private int statusFlag;
    // 申立て番号
    private String cid;
    // 件名
    private String caseTitle;
    // 登録日付
    private String petitionDate;
    // 状態
    private String caseStatus;
    // 対応期日
    private LocalDate correspondingDate;
    // 未読メッセージ件数
    private int numberOfUnreadMessages;
    // 要対応有無
    private int requiredSupport;

    public ReturnData(int statusFlag, String cid, String caseTitle, String petitionDate, String caseStatus,
            LocalDate orrespondingDate, int numberOfUnreadMessages, int requiredSupport) {
        setStatusFlag(statusFlag);
        setCid(cid);
        setCaseTitle(caseTitle);
        setPetitionDate(petitionDate);
        setCaseStatus(caseStatus);
        setCorrespondingDate(orrespondingDate);
        setNumberOfUnreadMessages(numberOfUnreadMessages);
        setRequiredSupport(requiredSupport);
    }

    public ReturnData() {
    }
}