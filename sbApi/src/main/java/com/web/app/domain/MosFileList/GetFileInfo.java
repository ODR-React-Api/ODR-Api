package com.web.app.domain.MosFileList;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * S7_申立てファイル一覧画面
 * API_ログインユーザのロールと開示情報取得
 * Dao层
 * GetFileInfo
 * ログインユーザのロールと開示情報戻り値
 * 
 * @author DUC 閆文静
 * @since 2024/04/25
 * @version 1.0
 */
@ApiModel
@Data
public class GetFileInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    // 調停人情報開示フラグ
    private Integer MediatorDisclosureFlag;
    // 申立て人
    private String PetitionUserId;
    // 相手方メール
    private String TraderUserEmail;
    // 調停人
    private String MediatorUserEmail;
    //立場フラグ
    private Integer PositionFlg;   
}
