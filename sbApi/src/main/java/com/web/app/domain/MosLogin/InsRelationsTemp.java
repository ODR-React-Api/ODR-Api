package com.web.app.domain.MosLogin;

import java.io.Serializable;
import lombok.Data;
 /**
 * S08_申立て登録画面
 * API_下書き用準備データ登録
 * Dao層
 * InsRelationsTemp
 * 
 * @author DUC 祭卉康
 * @since 2024/06/17
 * @version 1.0
 */
@Data
public class InsRelationsTemp implements Serializable {

    private static final long serialVersionUID = 1L;
    //自動採番ID
    private String id;
    //セッション.ユーザID
    private String userId;
    //ログインユーザ
    private String loginUser;

}
