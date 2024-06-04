package com.web.app.domain.MedUserChange;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * ファイル関連情報更新オブジェクト
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/06
 * @version 1.0
 */
@Data
public class InsertFileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    // ファイルID
    private String fileId;

    // 案件ID
    private String caseId;

    // ファイル名
    private String fileName;

    // 拡張子
    private String fileExtension;

    // URL
    private String fileUrl;

    // ファイルサイズ
    private int fileSize;

    // ファイル作成者ID
    private String registerUserId;

    // ファイル作成日付
    private Date registerDate;

    // 案件-添付ファイルリレーションID
    private String caseFileRelationsId;

    // 案件種類
    private int relationType;

    // 調停人担当案件履歴ID
    private String relatedId;

}
