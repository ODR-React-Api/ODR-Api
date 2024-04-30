package com.web.app.domain;
import java.io.Serializable;

import lombok.Data;

@Data
public class PetitionType  implements Serializable{
    private static final long serialVersionUID = 1L;
    //値
    private String value;
    //表示名
    private String displayName;
    //オーダー番号
    private Integer orderNo;
    //必須制御
    private Integer required;
    //master_lang.id
    private String languageId;
}

