package com.web.app.domain.MosContentConfirm;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class InsertCases implements Serializable {
    private static final long serialVersionUID = 1L;

    // ID
    private String cid;

    // プラットフォームID
    private String platformId;

    // 案件ステージ
    private String caseStage;

    // 案件ステータス
    private String caseStatus;

    // タイトル名
    private String caseTitle;

    // 申立て日
    private Date petitionDate;

    // 利用言語
    private String languageId;

    // 回答開始日
    private Date replyStartDate;

    // 回答期限日
    private Date replyEndDate;
}
