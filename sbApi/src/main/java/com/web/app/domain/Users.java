package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class Users implements Serializable {
  // 名前
  private String firstName;

  // 名字
  private String lastName;

  // 名前 カナ
  private String firstNamekana;

  // 名字 カナ
  private String lastNamekana;

  // 所属会社名
  private String companyName;

}
