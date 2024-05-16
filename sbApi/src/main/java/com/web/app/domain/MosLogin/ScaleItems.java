package com.web.app.domain.MosLogin;

import java.io.Serializable;
import lombok.Data;

/**
 * 拡張項目内容
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/25
 * @version 1.0
 */
@Data
public class ScaleItems implements Serializable {
    private static final long serialVersionUID = 1L;

    // 拡張項目Id
    private String ExtensionitemId;

    // 拡張項目値
    private String ExtensionitemValue;
}
