package com.web.app.domain;
import java.io.Serializable;

import lombok.Data;

@Data
public class CaseEstablish  implements Serializable{
    private static final long serialVersionUID = 1L;

    //セッション.セッション情報の和解案id
    private String caseNegotiationsId;

    //セッション情報の案件Case
    private String casesId;

    //ログインユーザ
    private String loginUser;
}
