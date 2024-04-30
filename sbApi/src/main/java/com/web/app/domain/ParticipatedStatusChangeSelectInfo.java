package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class ParticipatedStatusChangeSelectInfo implements Serializable {
    // ID
    private String cId;
    // 案件ステージ
    private Integer caseStage;
    // 案件ステータス
    private String caseStatus;

}
