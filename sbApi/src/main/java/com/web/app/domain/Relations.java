package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Relations  implements Serializable {
    // 戻り値
    private String uuId;
    private String userId;

    private static final long serialVersionUID = 1L;

}
