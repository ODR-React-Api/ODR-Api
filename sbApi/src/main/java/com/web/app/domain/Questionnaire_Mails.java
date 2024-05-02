package com.web.app.domain;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 当前对象如果作为了controller层方法的参数
 * 则生成的文档中，应该介绍当前对象的每一个属性 而不是直接介绍当前对象
 * 则需要在当前类中使用swagger提供的相关注解 来介绍 每一个属性以及当前类
 */
@ApiModel // 声明当前对象是用来封装数据的对象
@Data
public class Questionnaire_Mails implements Serializable {

  private QuestionnaireData questionnaireData;


  // private List<QuestionnaireData> questionnaireData;

  //アンケート回答済みかフラグ
  private boolean flag;

  private List<QuestionnaireList> questionnaireList;

}