package com.web.app.domain.Entity;
import java.io.Serializable;
import lombok.Data;

@Data
public class UserLanguageIdPlatformId implements Serializable {
    private static final long serialVersionUID = 1L;

    // 言語ID
    private String languageId;

    // プラットフォームID
    private String platformId;
}
