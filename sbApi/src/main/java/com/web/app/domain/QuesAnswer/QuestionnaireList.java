package com.web.app.domain.QuesAnswer;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * アンケートの問題リスト（questionnairesテーブル取得）
 * 
 * @author DUC 王亞テイ
 * @since 2024/04/17
 * @version 1.0
 */

@ApiModel
@Data
public class QuestionnaireList implements Serializable {

    private static final long serialVersionUID = 1L;

    // アンケート表示
    private String Description;

    // アンケートタイプ
    private Integer Type;

    // アクティブフラグ
    private Integer ActiveFlag;

    // 表示順
    private Integer Order;

    // 必須フラグ
    private Integer RequireFlag;

}