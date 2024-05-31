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

    public String id;

    public String PlatformId;

    public String CaseId;

    public String MediratorUserId;

    public String QuestionId;

    public Integer UserType;

    public String AnswerDate;

    public String result_Q1;

    public String result_Q2;

    public String result_Q3;

    public String result_Q4;

    public String result_Q5;

    public String result_Q6;

    public String result_Q7;

    public String result_Q8;

    public String result_Q9;

    public String result_Q10;

    public String result_Q11;

    public String result_Q12;

    public String result_Q13;

    public String result_Q14;

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
