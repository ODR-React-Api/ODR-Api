package com.web.app.domain;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SelectCondition  implements Serializable{
    private static final long serialVersionUID = 1L;
    //TBL「案件別個人情報リレーション」.案件ID
    private String caseId;
    //TBL「案件別個人情報リレーション」.申立て人
    private String PetitionUserId;
    //立場フラグ
    private int positionFlg;
    //申立て番号
    private String cid;
    //件名
    private String caseTitle;
    //登録日付From
    private Date petitionDateStart;
    //登録日付To
    private Date petitionDateEnd;
    //状態
    private String caseStatus;
}
