package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserInsertModel implements Serializable{

  private String uid;

  private String password;

  private String email;

  private String firstName;

  private String middleName;

  private String lastName;

  private String firstNameKana;

  private String middleNameKana;

  private String lastNameKana;

  private String companyName;

  private String platformId;

  private String languageId;

  private Integer status;

  private String timeZone;

  private String themeId1;

  private String themeId2;
  
  private String themeId3;
  
  private String themeId4;
  
  private Integer messageFrequency;

  private Integer termsConfirmed;

  private String confirmedVersionNoOfTerms;

  private String confirmedVersionNoOfPolicy;

  private Integer userType;

  private String other01;

  private String other02;

  private String other03;

  private String other04;

  private String other05;

  private Integer deleteFlag;

  private String lastModifiedBy;
}
