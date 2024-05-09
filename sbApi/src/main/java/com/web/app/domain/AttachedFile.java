package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 添付資料の取得
 * 
 * @author DUC 王亞テイ
 * @since 2024/04/23
 * @version 1.0
 */

@ApiModel
@Data
public class AttachedFile implements Serializable {

    private static final long serialVersionUID = 1L;

    // 添付ファイル名
    private String filename;
    // 添付ファイルURL
    private String fileUrl;

}