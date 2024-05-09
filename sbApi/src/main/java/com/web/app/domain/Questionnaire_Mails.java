package com.web.app.domain;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * アンケート情報取得データ
 * 
 * @author DUC 王亞テイ
 * @since 2024/04/17
 * @version 1.0
 */

@ApiModel
@Data
public class Questionnaire_Mails implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private QuestionnaireData questionnaireData;

    // アンケート回答済みかフラグ
    private boolean flag;

    private List<QuestionnaireList> questionnaireList;

}