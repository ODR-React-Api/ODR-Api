package com.web.app.domain.MosFileList;

import java.io.Serializable;
import lombok.Data;

 /**
 * S07_申立て詳細画面・ファイル
 * API_ログインユーザのロールと開示情報取得
 * Dao層
 * LoginUserInfo
 * 
 * @author DUC 祭卉康
 * @since 2024/05/20
 * @version 1.0
 */
@Data
public class LoginUserInfo implements Serializable{

    private static final long serialVersionUID = 1L;
    //ログインユーザId
    private String id;
    //セッション情報のcaseId
    private String caseId;
    //ログインユーザemail
    private String email;

}
