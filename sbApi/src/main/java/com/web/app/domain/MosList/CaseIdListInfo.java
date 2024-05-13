package com.web.app.domain.MosList;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class CaseIdListInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    // 案件ID
    private String caseId;
    // 申立て人
    private String petitionUserId;
    // 立場フラグ
    private Integer flag;
    // ユーザーID
    private String userId;

}