package com.web.app.domain;
import java.io.Serializable;

import lombok.Data;

@Data
public class SessionInfo  implements Serializable{
    private static final long serialVersionUID = 1L;
    //セッション.ユーザID
    private String sessionId;
    //セッション.PlatformId
    private String platformId;
}
