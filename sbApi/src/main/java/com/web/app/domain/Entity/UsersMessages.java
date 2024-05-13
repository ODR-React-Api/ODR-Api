package com.web.app.domain.Entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * ユーザメッセージ
 * 
 * @author DUC 王亞テイ
 * @since 2024/05/10
 * @version 1.0
 */

@ApiModel
@Data
public class UsersMessages {

    // ID
    private String id;

    // プラットフォームID
    private String PlatformId;

    // 案件ID
    private String CaseId;

    // メッセージグループID
    private String messageGroupId;

    // メッセージID
    private String messageId;

    // ユーザーID
    private String userId;

    //既読フラグ
    private Integer ReadFlag;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private Integer DeleteFlag;

    private Data LastModifiedDate;

    private String LastModifiedBy;

}
