package com.web.app.domain.Entity;

import java.io.Serializable;
import lombok.Data;

/**
 * QuestionnaireResults
 * テーブル名：questionnaire_resultsアンケート入力結果
 * 
 * @author DUC zzy
 * @since 2024/05/27
 * @version 1.0
 */
@Data
public class QuestionnaireResults implements Serializable {
    // ID
    public String id;
    // プラットフォームID

    public String PlatformId;
    // 案件ID

    public String CaseId;
    // 調停人ID

    public String MediratorUserId;
    // アンケートID

    public String QuestionId;
    // ユーザー立場

    public Integer UserType;
    // 回答日

    public String AnswerDate;
    // 回答1
    public String result_Q1;
    // 回答2

    public String result_Q2;
    // 回答3

    public String result_Q3;
    // 回答4

    public String result_Q4;
    // 回答5

    public String result_Q5;
    // 回答6

    public String result_Q6;
    // 回答7
    public String result_Q7;
    // 回答8
    public String result_Q8;
    // 回答9
    public String result_Q9;
    // 回答10
    public String result_Q10;
    // 回答11
    public String result_Q11;
    // 回答12
    public String result_Q12;
    // 回答13
    public String result_Q13;
    // 回答14
    public String result_Q14;
    // 回答15
    public String result_Q15;

    public String Other01;

    public String Other02;

    public String Other03;

    public String Other04;

    public String Other05;

    public Integer DeleteFlag;

    public String LastModifiedDate;

    public String LastModifiedBy;

    private static final long serialVersionUID = 1L;
}
