package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * questionnaire_mails テーブル取得
 * 
 * @author DUC 王亞テイ
 * @since 2024/04/17
 * @version 1.0
 */

@ApiModel
@Data
public class QuestionnaireData implements Serializable {

    private static final long serialVersionUID = 1L;

    // アンケートID
    private String QuestionId;

    // 送信対象ユーザEmail
    private String UserEmail;

    // ユーザ立場
    private Integer UserType;

    // 案件ID
    private String CaseId;

}