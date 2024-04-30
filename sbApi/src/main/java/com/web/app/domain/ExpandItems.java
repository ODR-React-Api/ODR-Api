package com.web.app.domain;
import java.io.Serializable;

import lombok.Data;

@Data
public class ExpandItems  implements Serializable{
    private static final long serialVersionUID = 1L;
    //項目表示名
    private String itemDisplayName;
    //項目種類( 0 = テキストボックス　1 = 数値のみテキストボックス)
    private Integer itemType;
    //必須フラグ(0 =任意項目とする 1=必須項目とする)
    private Integer isRequired;
}

