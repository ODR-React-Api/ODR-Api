package com.web.app.domain;
import java.io.Serializable;

import lombok.Data;

@Data
public class FileId  implements Serializable{
    private static final long serialVersionUID = 1L;

    //ファイル名
    private String fileName;
    //ファイルURL
    private String fileUrl;
}