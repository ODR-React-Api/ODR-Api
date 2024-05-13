package com.web.app.domain.MosDetail;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 拡張項目の取得
 * 
 * @author DUC 王亞テイ
 * @since 2024/04/23
 * @version 1.0
 */

@ApiModel
@Data
public class ExtensionItem implements Serializable {

    private static final long serialVersionUID = 1L;

    // 拡張項目名
    private String itemDisplayName;
    // 拡張項目値
    private String ExtensionitemValue;

}