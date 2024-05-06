package com.web.app.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel // 声明当前对象是用来封装数据的对象
@Data
public class LoginUser{
    // @ApiModelProperty在文档中介绍当前对象作为方法参数时，介绍当前属性
    @ApiModelProperty(name = "firstName", value = "名字", dataType = "String", required = false)
    private String firstName;

    @ApiModelProperty(name = "middleName", value = "中名", dataType = "String", required = false)
    private String middleName;

    @ApiModelProperty(name = "lastName", value = "姓氏", dataType = "String", required = false)
    private String lastName;

    @ApiModelProperty(name = "firstNameKana", value = "名字假名", dataType = "String", required = false)
    private String firstNameKana;

    @ApiModelProperty(name = "middleNameKana", value = "中名假名", dataType = "String", required = false)
    private String middleNameKana;

    @ApiModelProperty(name = "lastNameKana", value = "姓氏假名", dataType = "String", required = false)
    private String lastNameKana;

    @ApiModelProperty(name = "platformId", value = "平台id", dataType = "String", required = false)
    private String platformId;

    @ApiModelProperty(name = "languageId", value = "语言id", dataType = "String", required = false)
    private String languageId;

    @ApiModelProperty(name = "status", value = "地位", dataType = "int", required = false)
    private int status;

    @ApiModelProperty(name = "timeZone", value = "时区", dataType = "String", required = false)
    private String timeZone;

    @ApiModelProperty(name = "status", value = "规约确认版本", dataType = "int", required = false)
    private int confirmedVersionNoOfTerms;

    @ApiModelProperty(name = "status", value = "已确认的政策版本号", dataType = "int", required = false)
    private int confirmedVersionNoOfPolicy;

}
