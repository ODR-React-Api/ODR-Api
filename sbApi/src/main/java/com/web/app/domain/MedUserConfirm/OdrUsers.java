package com.web.app.domain.MedUserConfirm;

import java.io.Serializable;

import lombok.Data;

/**
 * 取得したユーザ情報
 * 
 * @author DUC 馬芹
 * @since 2024/05/21
 * @version 1.0
 */
@Data
public class OdrUsers implements Serializable {

    private static final long serialVersionUID = 1L;
   
    // 名前
    private String firstName;

    // 名字
    private String lastName;

    // 履歴書
    private String resumeFileId;

    // 自己紹介
    private String selfIntroduce;

    // 略歴
    private String historyInfo;

    // 専門分野
    private String major;

    // 職位
    private String position;

    // プロフィール画像
    private String profilePictureFileId;

    // ユーザ種類
    private int userType;

    // 所属会社名
    private String companyName;

    
}
