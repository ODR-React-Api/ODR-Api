package com.web.app.domain.MosContentConfirm;

import java.io.Serializable;
import lombok.Data;

/**
 * ユーザ情報
 * 
 * @author DUC 王魯興
 * @since 2024/05/29
 * @version 1.0
 */
@Data
public class UserLanguageIdPlatformId implements Serializable {
    private static final long serialVersionUID = 1L;

    // 言語ID
    private String languageId;

    // プラットフォームID
    private String platformId;
}
