package com.web.app.domain.MediationsConCon;
import lombok.Data;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

/**
 * S24_調停期日延長画面 調停案更新API
 * domain层
 * MediationsContentDB
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/09
 * @version 1.0
 */
@ApiModel 
@Data
public class MediationsContentDB implements Serializable{
    
    // 缓冲
    private static final long serialVersionUID = 1L;
    
    // ログインユーザ
    private String userId;

    // 調停案提出日
    private String submitDate;

    // 調停案内容
    private String htmlContext;

    // 調停合意書内容
    private String htmlContext2;

    // 最終更新日
    private String lastModifiedDate;

    // 最終更新人
    private String lastModifiedBy;

    // 案件ID
    private String caseId;

    // プラットフォームID
    private String platformId;

}
