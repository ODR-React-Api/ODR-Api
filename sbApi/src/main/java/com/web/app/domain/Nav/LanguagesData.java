package com.web.app.domain.Nav;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * N1 ナビバー画面
 * API_言語データ取得
 * Dao層
 * LanguagesData
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */
@ApiModel
@Data
public class LanguagesData implements Serializable {

    // 言語ID
    private String id;

    // 言語名
    private String languageName;

    private static final long serialVersionUID = 1L;

}
