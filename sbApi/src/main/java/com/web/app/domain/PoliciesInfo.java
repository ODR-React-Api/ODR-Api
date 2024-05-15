package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class PoliciesInfo implements Serializable { 
    // 内容
    private String HtmlContext;
    // バージョン番号
    private String VersionNo;

    private static final long serialVersionUID = 1L;
    
}
