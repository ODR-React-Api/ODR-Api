package com.web.app.domain.MosFileList;

import java.io.Serializable;

import lombok.Data;

 /**
 * 附件
 * 
 * @author DUC 祭卉康
 * @since 2024/05/27
 * @version 1.0
 */

 /**
 * 附件
 *
 * @param fileName 文件名称
 * @param fileUrl URL
 * @param registerUserId 注册用户ID
 * @param registerDate 注册日
 */

@Data
public class Files implements Serializable{

    private static final long serialVersionUID = 1L;

    private String fileName;

    private String fileUrl;

    private String registerUserId;

    private String registerDate;


}
