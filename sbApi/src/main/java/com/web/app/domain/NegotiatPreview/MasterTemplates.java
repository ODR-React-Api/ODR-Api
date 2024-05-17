package com.web.app.domain.NegotiatPreview;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 和解案テンプレート
 * 
 * @author DUC 馬芹
 * @since 2024/05/14
 * @version 1.0
 */
@Data
public class MasterTemplates implements Serializable {

    private static final long serialVersionUID = 1L;
    //ID
    private String id;
    //プラットフォームID
    private String platformId;
    //テンプレートID
    private String templateId;
    //登録日
    private String startDate;
    //テンプレートの種類
    private int templateType;
    //言語
    private String languageId;
    //テンプレート名
    private String templateName;
    //テンプレート内容
    private String context;
    //使用回数
    private int usageCount;
    //利用・停止フラグ
    private int isActive;
    private String other01;
    private String other02;
    private String other03;
    private String other04;
    private String other05;
    private int deleteFlag;
    private String lastModifiedDate;
    private String lastModifiedBy;
    //0:和解案,1:調停案,2:仲裁案,3:和解案合意書, 4: 調停案合意書
    private List<Integer> templateTypes;
    
}
