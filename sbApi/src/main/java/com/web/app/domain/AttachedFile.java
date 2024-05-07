package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel // 声明当前对象是用来封装数据的对象
@Data
public class AttachedFile implements Serializable {
  // 添付ファイル名
  private String filename;
  // 添付ファイルURL
  private String fileUrl;

}