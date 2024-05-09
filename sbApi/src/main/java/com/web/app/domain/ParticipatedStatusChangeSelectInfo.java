package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class ParticipatedStatusChangeSelectInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    // ID
    private String cId;
    // 案件ステージ
    private Integer caseStage;
    // 案件ステータス
    private String caseStatus;

}
