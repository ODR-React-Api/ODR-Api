package com.web.app.domain.NegotiatAgree;

import java.io.Serializable;
import lombok.Data;

/**
 * API「案件成立更新」用の画面項目
 * 
 * @author DUC 馮淑慧
 * @since 2024/05/09
 * @version 1.0
 */
@Data
public class CaseEstablish implements Serializable {
    private static final long serialVersionUID = 1L;

    // セッション情報の和解案id
    private String caseNegotiationsId;

    // セッション情報の案件Case
    private String casesId;

    // ログインユーザ
    private String loginUser;
}
