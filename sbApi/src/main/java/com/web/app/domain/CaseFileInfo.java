package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel // 声明当前对象是用来封装数据的对象
@Data
public class CaseFileInfo implements Serializable {
    // ファイル名
    private String fileName;
    // URL
    private String fileUrl;
    // ユーザーID
    private String registerUserId;
    // 登録日
    private String registerDate;

    private static final long serialVersionUID = 1L;
    
}
