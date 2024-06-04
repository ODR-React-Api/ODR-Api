package com.web.app.domain.Entity;

import java.io.Serializable;
import lombok.Data;

/**
 * C09_アンケート回答確認画面
 * Dao層
 * InsQuestionnaireResults
 * 
 * @author DUC zzy
 * @since 2024/05/27
 * @version 1.0
 */
@Data
public class InsQuestionnairesResults implements Serializable {
    // サーバ設定.platformId

    private String platformId;
    // 【画面C08】.caseId

    private String caseId;
    // 【画面C08】.アンケートID

    private String questionId;
    // 【画面C08】.ユーザ立場

    private Integer userType;
    // 画面入力.Q1

    private String resultQ1;
    // 画面入力.Q2

    private String resultQ2;
    // 画面入力.Q3

    private String resultQ3;
    // 画面入力.Q4

    private String resultQ4;
    // 画面入力.Q5

    private String resultQ5;
    // 画面入力.Q6

    private String resultQ6;
    // 画面入力.Q7

    private String resultQ7;
    // 画面入力.Q8

    private String resultQ8;
    // 画面入力.Q9

    private String resultQ9;
    // 画面入力.Q10

    private String resultQ10;
    // 画面入力.Q11

    private String resultQ11;
    // 画面入力.Q12

    private String resultQ12;
    // 画面入力.Q13

    private String resultQ13;
    // 画面入力.Q14

    private String resultQ14;
    // 画面入力.Q15

    private String resultQ15;
    // 【画面C08】.userEmail

    private String userEmail;

    private static final long serialVersionUID = 1L;
}
