package com.web.app.domain.MosContentConfirm;

import java.io.Serializable;
import lombok.Data;

@Data
public class ExtensionItem implements Serializable {
    private static final long serialVersionUID = 1L;

    //画面上拡張項目の項目Id
    private String extensionitemId;

    //画面上拡張項目の項目値
    private String extensionitemValue;
}
