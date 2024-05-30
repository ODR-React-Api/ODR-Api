package com.web.app.domain.MosContentConfirm;

import java.io.Serializable;
import lombok.Data;

/**
 * 画面上拡張項目
 * 
 * @author DUC 王魯興
 * @since 2024/05/29
 * @version 1.0
 */
@Data
public class ExtensionItem implements Serializable {
    private static final long serialVersionUID = 1L;

    // 画面上拡張項目の項目Id
    private String extensionitemId;

    // 画面上拡張項目の項目値
    private String extensionitemValue;
}
