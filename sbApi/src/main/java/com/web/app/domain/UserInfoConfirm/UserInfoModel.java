package com.web.app.domain.UserInfoConfirm;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class UserInfoModel implements Serializable{

    private static final long serialVersionUID = 1L;

    private String password;

    private String email;

    private String firstName;

    private String middleName;

    private String lastName;

    private String firstNameKana;

    private String middleNameKana;

    private String lastNameKana;

    private String companyName;

    private String lastModifiedBy;
}
