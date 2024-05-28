package com.web.app.domain.MosFileList;

import java.io.Serializable;
import lombok.Data;

 /**
 * S07_申立て詳細画面・ファイル
 * API_案件添付ファイル取得
 * Dao層
 * Files
 * 
 * @author DUC 祭卉康
 * @since 2024/05/20
 * @version 1.0
 */
@Data
public class Files implements Serializable{

    private static final long serialVersionUID = 1L;
    //ファイル名
    private String fileName;
    //URL
    private String fileUrl;
    //ユーザーID
    private String registerUserId;
    //登録日
    private String registerDate;

}
