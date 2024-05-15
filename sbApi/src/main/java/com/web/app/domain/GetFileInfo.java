package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class GetFileInfo implements Serializable {
    // 調停人情報開示フラグ
    private Integer MediatorDisclosureFlag;
    // 申立て人
    private String PetitionUserId;
    // 相手方メール
    private String TraderUserEmail;
    // 調停人
    private String MediatorUserEmail;
    //立場フラグ
    private Integer PositionFlg;

    private static final long serialVersionUID = 1L;
    
}
