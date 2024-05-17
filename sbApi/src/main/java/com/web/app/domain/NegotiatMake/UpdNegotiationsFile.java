package com.web.app.domain.NegotiatMake;

import java.io.Serializable;
import lombok.Data;

/**
 * 画面からのファイル項目
 * 
 * @author DUC 馬芹
 * @since 2024/05/06
 * @version 1.0
 */
@Data
public class UpdNegotiationsFile implements Serializable {
    private static final long serialVersionUID = 1L;
    //追加の場合は空白
    private String id;
    //ファイル名
    private String fileName;
    //拡張子
    private String fileExtension;
    //URL
    private String fileUrl;
    //ファイルサイズ
    private int fileSize;
    //0:削除ファイル
    //1:未処理ファイル
    //2：追加ファイル
    private int updFileFlag;

}
