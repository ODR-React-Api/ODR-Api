package com.web.app.domain.getSelectListInfo;


import java.io.Serializable;

import lombok.Data;

 /**
    *API「検索用ケース詳細取得」を呼び出す。
        引数：①取得したCaseId
		①取得したPetitionUserId
		立場フラグ
		引数.申立て番号
		引数.件名
		引数.登録日付From
		引数.登録日付To
		引数.ステータス
     * @author DUC 郝建润
     * @since 2024/06/04
     * @version 1.0
     */
@Data
public class ParameterData implements Serializable{
    private static final long serialVersionUID = 1L;
    //①取得したCaseId
    private String caseId;
    //①取得したPetitionUserId
    private String petitionUserId;
    //立場フラグ＝1（申立人）
    private int statusFlag;
    //引数.申立て番号
    private String cid;
    //引数.件名
    private String caseTitle;
    //引数.登録日付From
    private String petitionDateForm;
    //引数.登録日付To
    private String petitionDateTo;
    //状態
    private String caseStatus;

    public ParameterData(String caseId, String petitionUserId, int statusFlag, String cid, String caseTitle,
    String petitionDateForm,  String petitionDateTo, String caseStatus) {
                setCaseId(caseId);
                setPetitionUserId(petitionUserId);
                setStatusFlag(statusFlag);
                setCid(cid);
                setCaseTitle(caseTitle);
                setPetitionDateForm(petitionDateForm);
                setPetitionDateTo(petitionDateTo);
                setCaseStatus(caseStatus);
    }
}