package com.web.app.domain.MosLogin;

import java.io.Serializable;
import lombok.Data;

/**
 * TBL「案件-添付ファイルリレーション（case_file_relations）」より関連下書き案件のファイルID内容
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/25
 * @version 1.0
 */
@Data
public class FileId implements Serializable {
    private static final long serialVersionUID = 1L;

    // ファイル名
    private String FileName;

    // ファイルURL
    private String FileUrl;
}
