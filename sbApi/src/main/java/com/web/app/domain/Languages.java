package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel // 声明当前对象是用来封装数据的对象
@Data
public class Languages implements Serializable {
  
    //ID
    private String id;
    
    //言語名
    private String languageName;

    // private Boolean DeleteFlag;

    private static final long serialVersionUID = 1L;

}
