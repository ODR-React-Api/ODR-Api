package com.web.app.domain;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 当前对象如果作为了controller层方法的参数
 * 则生成的文档中，应该介绍当前对象的每一个属性 而不是直接介绍当前对象
 * 则需要在当前类中使用swagger提供的相关注解 来介绍 每一个属性以及当前类
 */
@ApiModel // 声明当前对象是用来封装数据的对象
@Data
public class User implements Serializable {
    // @ApiModelProperty在文档中介绍当前对象作为方法参数时，介绍当前属性
    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer", required = true)
    private Integer id;

    @ApiModelProperty(name = "name", value = "用户名", dataType = "String", required = true)
    private String name;

    @ApiModelProperty(name = "portrait", value = "用户头像地址", dataType = "String", required = false)
    private String portrait;

    private String phone;

    private String password;

    private String regIp;

    private Boolean accountNonExpired;

    private Boolean credentialsNonExpired;

    private Boolean accountNonLocked;

    private String status;

    private Boolean isDel;

    private Date createTime;

    private Date updateTime;

    private String firstName;

    private String middleName;

    private String lastName;

    private static final long serialVersionUID = 1L;
}