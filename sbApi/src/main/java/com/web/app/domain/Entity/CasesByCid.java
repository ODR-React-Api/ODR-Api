package com.web.app.domain.Entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 反訴回答登録画面 domain
 * 
 * @author DUC 信召艶
 * @since 2024/05/06
 * @version 1.0
 */
@ApiModel 
@Data
public class CasesByCid implements Serializable{
    //バッファリング    
    private static final long serialVersionUID = 1L;
    //タイトル名
    private String CaseTitle;
}
