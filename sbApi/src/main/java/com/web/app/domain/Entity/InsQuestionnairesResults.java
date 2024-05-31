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

    private String platformId;

    private String caseId;

    private String questionId;

    private Integer userType;

    private String resultQ1;

    private String resultQ2;

    private String resultQ3;

    private String resultQ4;

    private String resultQ5;

    private String resultQ6;

    private String resultQ7;

    private String resultQ8;

    private String resultQ9;

    private String resultQ10;

    private String resultQ11;

    private String resultQ12;

    private String resultQ13;

    private String resultQ14;

    private String resultQ15;

    private String userEmail;

    private static final long serialVersionUID = 1L;
}
