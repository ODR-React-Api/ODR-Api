package com.web.app.domain.Entity;

import lombok.Data;

/**
 * 種類マスタ
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/10
 * @version 1.0
 */
@Data
public class MasterTypes {
    
    // ID
    private String Id;

    // プラットフォームID
    private String platformId;

    // 申立種類、希望する解決方法種類、回答種類、対応方法の種類など
    private String type;

    // 値
    private String Value;

    // 表示名
    private String DisplayName;

    // オーダー番号
    private int OrderNo;

    // 必須制御
    private Integer Required;

    // 利用・停止制御
    private Integer IsActive;

    // master_lang.id
    private String LanguageId;

    // 作成者
    private String Creator;

    // 作成日
    private String CreateDate;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private Integer DeleteFlag;

    private String LastModifiedDate;

    private String LastModifiedBy;
}
