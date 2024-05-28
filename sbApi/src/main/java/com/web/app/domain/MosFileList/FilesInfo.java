package com.web.app.domain.MosFileList;

import java.io.Serializable;
import lombok.Data;

 /**
 * S07_申立て詳細画面・ファイル
 * API_案件添付ファイル取得
 * Dao層
 * FilesInfo
 * 
 * @author DUC 祭卉康
 * @since 2024/05/20
 * @version 1.0
 */
@Data
public class FilesInfo implements Serializable{

    private static final long serialVersionUID = 1L;
    //ログインユーザ
    private String id;
    //セッション情報のcaseid
    private String caseid;

}
