package com.web.app.domain.MosFileList;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * S7_申立てファイル一覧画面
 * API_案件添付ファイル取得
 * Dao层
 * CaseFileInfo
 * 案件添付ファイル戻り値
 * 
 * @author DUC 閆文静
 * @since 2024/04/25
 * @version 1.0
 */
@ApiModel
@Data
public class CaseFileInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    // ファイル名
    private String fileName;
    // URL
    private String fileUrl;
    // ユーザーID
    private String registerUserId;
    // 登録日
    private String registerDate;  
}
