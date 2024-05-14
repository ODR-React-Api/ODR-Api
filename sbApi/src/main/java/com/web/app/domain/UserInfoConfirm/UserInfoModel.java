package com.web.app.domain.UserInfoConfirm;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class UserInfoModel implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "密码", name = "password", dataType = "String", required = true)
    private String password;

    @ApiModelProperty(value = "邮箱", name = "Email", dataType = "String")
    private String email;

    @ApiModelProperty(value = "氏名(姓)", name = "FirstName", dataType = "String")
    private String firstName;

    @ApiModelProperty(value = "氏名(名)", name = "MiddleName", dataType = "String")
    private String middleName;

    @ApiModelProperty(value = "氏名(姓)", name = "LastName", dataType = "String")
    private String lastName;

    @ApiModelProperty(value = "カナ(姓)", name = "FirstNameKana", dataType = "String")
    private String firstNameKana;

    @ApiModelProperty(value = "カナ(名)", name = "middleNameKana", dataType = "String")
    private String middleNameKana;

    @ApiModelProperty(value = "カナ(名)", name = "lastNameKana", dataType = "String")
    private String lastNameKana;

    @ApiModelProperty(value = "所属会社", name = "CompanyName", dataType = "String")
    private String companyName;

    @ApiModelProperty(value = "登录用户", name = "lastModifiedBy", dataType = "String")
    private String lastModifiedBy;
}
