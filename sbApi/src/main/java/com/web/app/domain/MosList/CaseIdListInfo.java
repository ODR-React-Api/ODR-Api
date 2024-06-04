package com.web.app.domain.MosList;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * API_一覧取得より渡された引数
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/17
 * @version 1.0
 */
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
