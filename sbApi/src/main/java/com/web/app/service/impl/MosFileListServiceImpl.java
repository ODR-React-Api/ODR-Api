package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.web.app.domain.MosFileList.Files;

import com.web.app.domain.MosFileList.UserIdentity;

import com.web.app.mapper.GetFileInfoMapper;

import com.web.app.mapper.GetLoginUserRoleOpenInfoMapper;

import com.web.app.service.MosFileListService;

/**
 * 工具类ServiceImpl
 * 
 * @author DUC 祭卉康
 * @since 2024/05/20
 * @version 1.0
 */

@Service
public class MosFileListServiceImpl implements MosFileListService {

    @Autowired
    private GetLoginUserRoleOpenInfoMapper getLoginUserRoleOpenInfoMapper;

    @Autowired
    private GetFileInfoMapper getFileInfoMapper;

    UserIdentity userIdentity = new UserIdentity();

     /**
     * 登录用户的角色和公开信息获取API
     *
     * @param id odr_users的Uid
     * @param email odr_users的Email
     * @param caseid 会话信息的caseid
     * @param flag  值为1 用户为申请人，值为2用户为对方，值为3用户为调解人
     * @return 用户身份的信息
     */

    @Override
    public UserIdentity userIdentity(String id,  String caseid, String email) {
     
        userIdentity = getLoginUserRoleOpenInfoMapper.findGetLoginUserRoleOpenInfo(caseid);
        
        String petitionUserId = userIdentity.getPetitionUserId();

        String traderUserEmail = userIdentity.getTraderUserEmail();

        String mediatorUserEmail = userIdentity.getMediatorUserEmail();
        
        if (id.equals(petitionUserId)) {

            userIdentity.setFlag(1);
        }

        if (email.equals(traderUserEmail)) {

            userIdentity.setFlag(2);
        }

        if (email.equals(mediatorUserEmail) ) {

            userIdentity.setFlag(3);
        }

        return userIdentity;

    }

     /**
     * 附件获取API
     *
     * @param id odr_users的Uid
     * @param caseid 会话信息的caseid
     * @param flag  值为1 用户为申请人，值为2用户为对方，值为3用户为调解人
     * @param mediatorDisclosureFlag 调解人信息披露标志
     * @return 用户身份的信息
     */
    
    @Override
    public Files files(String id, String caseid) {

        int flag = userIdentity.getFlag();

        int mediatorDisclosureFlag = userIdentity.getMediatorDisclosureFlag();

        Files files = getFileInfoMapper.findGetFileInfo(caseid, id, flag, mediatorDisclosureFlag);

        return files;

    }

}
