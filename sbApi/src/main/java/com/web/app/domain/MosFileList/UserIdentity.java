package com.web.app.domain.MosFileList;

import java.io.Serializable;

import lombok.Data;

 /**
 * 用户信息获取
 * 
 * @author DUC 祭卉康
 * @since 2024/05/20
 * @version 1.0
 */

 /**
 * 用户信息获取
 *
 * @param mediatorDisclosureFlag 调解人信息披露标志
 * @param petitionUserId 申请人
 * @param traderUserEmail 对方邮件
 * @param mediatorUserEmail 调解人
 * @param flag 用户判定标志
 */

@Data
public class UserIdentity implements Serializable{

    private static final long serialVersionUID = 1L;

    private int mediatorDisclosureFlag;
    
    private String petitionUserId;

    private String traderUserEmail;

    private String mediatorUserEmail;

    private int flag;
    
}
