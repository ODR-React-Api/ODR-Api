package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class CaseFileInfo implements Serializable {
    // ファイル名
    private String FindMailTemplatesListileName;
    // URL
    private String FileUrl;
    // ユーザーID
    private String RegisterUserId;
    // 登録日
    private String RegisterDate;

    private static final long serialVersionUID = 1L;
    
}
