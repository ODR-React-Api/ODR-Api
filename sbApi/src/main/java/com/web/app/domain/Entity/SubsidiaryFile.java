package com.web.app.domain.Entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class SubsidiaryFile implements Serializable {
    //缓冲
    private static final long serialVersionUID = 1L; 
    //ファイル名
    private String fileName;
    //ファイルURL
    private String fileUrl;
}
