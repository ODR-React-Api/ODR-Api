package com.web.app.domain;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * S1_利用規約確認画面
 * API_利用規約情報取得
 * Dao层
 * PoliciesInfo
 * 利用規約情報戻り値
 * 
 * @author DUC 閆文静
 * @since 2024/04/19
 * @version 1.0
 */
@ApiModel
@Data
public class PoliciesInfo implements Serializable { 
    private static final long serialVersionUID = 1L;
    // 内容
    private String HtmlContext;
    // バージョン番号
    private String VersionNo;   
}
