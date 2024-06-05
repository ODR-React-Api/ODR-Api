package com.web.app.domain.getSelectListInfo;

import java.io.Serializable;

import lombok.Data;

/**
 * ケース
 * 
 * @author DUC 郝建润
 * @since 2024/06/04
 * @version 1.0
 */
@Data
public class CaseRelations implements Serializable {
    private static final long serialVersionUID = 1L;
    // 案件ID
    private String caseId;
    // 申立て人
    private String petitionUserId;
}