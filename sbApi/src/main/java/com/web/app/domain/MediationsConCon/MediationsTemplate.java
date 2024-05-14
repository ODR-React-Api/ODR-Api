package com.web.app.domain.MediationsConCon;

import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;

/**
 * S24_調停期日延長画面 調停案テンプレート取得API
 * domain层
 * MediationsTemplate
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/09
 * @version 1.0
 */
@ApiModel 
@Data
public class MediationsTemplate implements Serializable{
    // 缓冲
    private static final long serialVersionUID = 1L;
    
    // テンプレート内容
    private String context;

    // プラットフォームID
    private String platformId;

    // 言語
    private String languageId;


}
