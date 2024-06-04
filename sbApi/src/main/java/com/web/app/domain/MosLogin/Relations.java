package com.web.app.domain.MosLogin;



import java.io.Serializable;

import lombok.Data;

@Data
public class Relations  implements Serializable {
    // 自動採番uuId
    private String uuId;
    // ユーザID
    private String userId;

    private static final long serialVersionUID = 1L;

}
