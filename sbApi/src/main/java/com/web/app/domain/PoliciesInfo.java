package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel // 声明当前对象是用来封装数据的对象
@Data
public class PoliciesInfo implements Serializable { 
    private String htmlContext;

    private String versionNo;

    private static final long serialVersionUID = 1L;
    
}
