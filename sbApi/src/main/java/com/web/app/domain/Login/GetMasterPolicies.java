package com.web.app.domain.Login;

import java.io.Serializable;

import lombok.Data;

/**
 * 利用規約、ポリシー管理情報取得内容
 * 
 * @author DUC 耿浩哲
 * @since 2024/06/12
 * @version 1.0
 */
@Data
public class GetMasterPolicies implements Serializable {
    private static final long serialVersionUID = 1L;

    // プラットフォームID
    private String platformId;

    // 言語ID
    private String languageId;

    // タイプ
    private Integer policyType;

    // バージョン番号
    private Integer versionNo;
}
