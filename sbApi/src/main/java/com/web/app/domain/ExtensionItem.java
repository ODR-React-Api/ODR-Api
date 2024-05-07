package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel // 声明当前对象是用来封装数据的对象
@Data
public class ExtensionItem implements Serializable {
  // 拡張項目名
  private String itemDisplayName;
  // 拡張項目値
  private String ExtensionitemValue;

}