package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class QuestionnaireList implements Serializable {
  //アンケート表示
  private String Description;

  //アンケートタイプ
  private Integer Type;

  //アクティブフラグ
  private Integer ActiveFlag;

  //表示順
  private Integer Order;

  //必須フラグ
  private Integer RequireFlag;

}