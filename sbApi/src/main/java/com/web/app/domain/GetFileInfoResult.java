package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class GetFileInfoResult  implements Serializable {
    //立場フラグ
    private Integer positionFlg;

    private static final long serialVersionUID = 1L;
    
}
