package com.web.app.domain.MosDetail;

import java.io.Serializable;
import lombok.Data;

/**
 * ユーザ情報
 * 
 * @author DUC 王魯興
 * @since 2024/05/29
 * @version 1.0
 */
@Data
public class UserCase implements Serializable {
    private static final long serialVersionUID = 1L;

    // ケースId
    private String caseId;

    // ユーザーId
    private String petitionUserId;
}
