package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.MosFileList.Files;
import com.web.app.domain.MosFileList.LoginUserRoleOpenInfo;
import com.web.app.mapper.GetFileInfoMapper;
import com.web.app.mapper.GetLoginUserRoleOpenInfoMapper;
import com.web.app.service.MosFileListService;

/**
 * S07_申立て詳細画面・ファイル
 * Service層実現類
 * MosFileListServiceImpl
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
    //ユーザ情報取得
    LoginUserRoleOpenInfo getLoginUserRoleOpenInfo = new LoginUserRoleOpenInfo();

     /**
     * API_ログインユーザのロールと開示情報取得
     *
     * @param id ログインユーザId
     * @param email ログインユーザemail
     * @param caseId セッション情報のcaseid
     * @return 取得API_ログインユーザのロールと開示情報
     */
    @Override
    public LoginUserRoleOpenInfo loginUserRoleOpenInfo(String id,  String caseId, String email) {
     
        getLoginUserRoleOpenInfo = getLoginUserRoleOpenInfoMapper.getLoginUserRoleOpenInfo(caseId);
        String petitionUserId = getLoginUserRoleOpenInfo.getPetitionUserId();
        String traderUserEmail = getLoginUserRoleOpenInfo.getTraderUserEmail();
        String mediatorUserEmail = getLoginUserRoleOpenInfo.getMediatorUserEmail();
        
        if (id.equals(petitionUserId)) {
            //ログインユーザIdがPetitionUserIdと一致すれば、申立人とする
            getLoginUserRoleOpenInfo.setFlag(1);
        }
        if (email.equals(traderUserEmail)) {
            //ログインユーザemailがTraderUserEmailと一致すれば、相手方とする
            getLoginUserRoleOpenInfo.setFlag(2);
        }
        if (email.equals(mediatorUserEmail) ) {
            //ログインユーザemailがMediatorUserEmailと一致すれば、調停人とする
            getLoginUserRoleOpenInfo.setFlag(3);
        }
        return getLoginUserRoleOpenInfo;

    }

     /**
     * API_案件添付ファイル取得
     *
     * @param id ログインユーザ
     * @param caseId セッション情報のcaseid
     * @return 取得案件添付ファイル
     */
    @Override
    public Files files(String id, String caseId) {

        int flag = getLoginUserRoleOpenInfo.getFlag();
        int mediatorDisclosureFlag = getLoginUserRoleOpenInfo.getMediatorDisclosureFlag();
        Files files = getFileInfoMapper.getFileInfo(id, caseId, flag, mediatorDisclosureFlag);
        return files;

    }

}
