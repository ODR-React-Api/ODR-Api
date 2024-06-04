package com.web.app.domain.MosDetail;

import java.io.Serializable;
import lombok.Data;

/**
 * S04_申立て概要画面
 * Dao層
 * CaseIdParameter
 * 引数: 渡し項目.CaseId
 * 
 * @author DUC 張明慧
 * @since 2024/04/25
 * @version 1.0
 */
@Data
public class CaseIdParameter implements Serializable {
    // ID
    private String caseId;

    private static final long serialVersionUID = 1L;
}
