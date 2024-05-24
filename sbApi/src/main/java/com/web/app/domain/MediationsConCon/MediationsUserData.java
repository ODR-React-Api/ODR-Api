package com.web.app.domain.MediationsConCon;

import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;

/**
 * S24_調停期日延長画面 ユーザデータ取得API
 * domain层
 * MediationsUserData
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/09
 * @version 1.0
 */
@ApiModel
@Data
public class MediationsUserData implements Serializable{
    // 缓冲
    private static final long serialVersionUID = 1L;
    
    // 名前
    private String firstName;

    // ミドルネーム
    private String middleName;

    // 名字
    private String lastName;

    // 所属会社名
    private String companyName;

}
