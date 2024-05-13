package com.web.app.domain.MosDetail;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel // 声明当前对象是用来封装数据的对象
@Data
public class MediatorHistories implements Serializable {
  // 受理状態
  private int status;
  // 辞任フラグ
  private int resignFlag;

}