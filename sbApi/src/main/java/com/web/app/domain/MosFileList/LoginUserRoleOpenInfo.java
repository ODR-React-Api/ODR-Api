package com.web.app.domain.MosFileList;

import java.io.Serializable;
import lombok.Data;

 /**
 * S07_申立て詳細画面・ファイル
 * API_ログインユーザのロールと開示情報取得
 * Dao層
 * LoginUserRoleOpenInfo
 * 
 * @author DUC 祭卉康
 * @since 2024/05/20
 * @version 1.0
 */
@Data
public class LoginUserRoleOpenInfo implements Serializable{

    private static final long serialVersionUID = 1L;
    //調停人情報開示フラグ
    private int mediatorDisclosureFlag;
    //申立て人
    private String petitionUserId;
    //相手方メール
    private String traderUserEmail;
    //調停人
    private String mediatorUserEmail;
    //ユーザー判定フラグ
    private int flag;
    
}
