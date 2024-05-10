package com.web.app.domain.mediationsMake;

import java.io.Serializable;
import lombok.Data;


/**
 * 調停案データ取得API 添付ファイルdomain
 * 
 * @author DUC 徐義然
 * @since 2024/05/07
 * @version 1.0
 */
@Data
public class SubsidiaryFile implements Serializable {
    //バッファリング
    private static final long serialVersionUID = 1L; 
    //ファイル名
    private String fileName;
    //ファイルURL
    private String fileUrl;
}
