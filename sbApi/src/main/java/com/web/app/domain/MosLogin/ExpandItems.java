package com.web.app.domain.MosLogin;

import java.io.Serializable;
import lombok.Data;

/**
 * 申立て登録画面の拡張項目の内容
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/23
 * @version 1.0
 */
@Data
public class ExpandItems implements Serializable {
    private static final long serialVersionUID = 1L;

    // 項目表示名
    private String ItemDisplayName;

    // 項目種類( 0 = テキストボックス 1 = 数値のみテキストボックス)
    private Integer ItemType;

    // 必須フラグ(0 =任意項目とする 1=必須項目とする)
    private Integer IsRequired;
}
