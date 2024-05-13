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
    public String Id;

    // プラットフォームID
    public String platformId;

    // 申立種類、希望する解決方法種類、回答種類、対応方法の種類など
    public String type;

    // 値
    public String Value;

    // 表示名
    public String DisplayName;

    // オーダー番号
    public int OrderNo;

    // 必須制御
    public Integer Required;

    // 利用・停止制御
    public Integer IsActive;

    // master_lang.id
    public String LanguageId;

    // 作成者
    public String Creator;

    // 作成日
    public String CreateDate;

    public String Other01;

    public String Other02;

    public String Other03;

    public String Other04;

    public String Other05;

    public Integer DeleteFlag;

    public String LastModifiedDate;

    public String LastModifiedBy;
}
