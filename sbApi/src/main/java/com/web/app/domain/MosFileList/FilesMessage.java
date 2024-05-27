package com.web.app.domain.MosFileList;

import java.io.Serializable;

import lombok.Data;

/**
 * 登录用户的信息
 * 
 * @author DUC 祭卉康
 * @since 2024/05/27
 * @version 1.0
 */

 /**
 * 登录用户的信息
 *
 * @param id odr_users的Uid
 * @param caseid 会话信息的caseid
 */

@Data
public class FilesMessage implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;

    private String caseid;

}
