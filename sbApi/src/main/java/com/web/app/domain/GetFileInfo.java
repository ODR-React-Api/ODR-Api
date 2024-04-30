package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel // 声明当前对象是用来封装数据的对象
@Data
public class GetFileInfo implements Serializable {
    // 調停人情報開示フラグ
    private Integer mediatorDisclosureFlag;
    // 申立て人
    private String petitionUserId;
    // 相手方メール
    private String traderUserEmail;
    // 調停人
    private String mediatorUserEmail;
    //立場フラグ
    private Integer positionFlg;

    private static final long serialVersionUID = 1L;
    
}
