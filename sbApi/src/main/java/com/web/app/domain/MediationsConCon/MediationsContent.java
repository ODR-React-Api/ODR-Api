package com.web.app.domain.MediationsConCon;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel 
@Data
public class MediationsContent implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    // ログインユーザ
    private String userId;

    // 調停案提出日
    private Date submitDate;

    // 調停案内容
    private String htmlContext;

    // 調停合意書内容
    private String htmlContext2;

    // 最終更新日
    private Date lastModifiedDate;

    // 最終更新人
    private String lastModifiedBy;

    // 案件ID
    private String caseId;

    // プラットフォームID
    private String platformId;

}
