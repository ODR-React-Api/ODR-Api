package com.web.app.domain;
import java.io.Serializable;
import lombok.Data;

@Data
public class CaseDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    // ケースId
    private String caseId;

    // ユーザーId
    private String petitionUserId;

    // 立場フラグ
    private Integer flag;

    // セッション.ユーザID
    private String uid;
}
