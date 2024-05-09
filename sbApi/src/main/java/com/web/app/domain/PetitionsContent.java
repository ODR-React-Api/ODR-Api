package com.web.app.domain;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 申立ての内容の取得
 * 
 * @author DUC 王亞テイ
 * @since 2024/04/23
 * @version 1.0
 */

@ApiModel
@Data
public class PetitionsContent implements Serializable {

    private static final long serialVersionUID = 1L;

    // 申立ての内容
    private CasePetitions casePetitions;

    // 添付資料
    private List<AttachedFile> attachedFile;

    // 拡張項目
    private List<ExtensionItem> extensionItem;

}