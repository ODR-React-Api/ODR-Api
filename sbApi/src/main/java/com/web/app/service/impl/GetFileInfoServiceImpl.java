package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.CaseFileInfo;
import com.web.app.domain.GetFileInfo;
import com.web.app.domain.GetFileInfoResult;
import com.web.app.mapper.GetFileInfoMapper;
import com.web.app.service.GetFileInfoService;


@Service
public class GetFileInfoServiceImpl implements GetFileInfoService {

    @Autowired
    private GetFileInfoMapper loginUserRoleOpenInfoMapper;

    @Override
    public GetFileInfo getLoginUserRoleOpenInfo(String caseId,String id,String email) {
        GetFileInfo loginUserRoleOpenInfo = new GetFileInfo();
        GetFileInfoResult loginUserRoleOpenInfoResult = new GetFileInfoResult();
        try{
            // ログインユーザのロールと開示情報取得API
            loginUserRoleOpenInfo = loginUserRoleOpenInfoMapper.selectLoginUserRoleOpenInfo(caseId);
        } catch (Exception e) {
            System.out.println("ログインユーザのロールと開示情報取得失敗");
        }

        // ログインユーザIdがPetitionUserIdと一致すれば、申立人とする
        if(loginUserRoleOpenInfo.getPetitionUserId().equals(id)){
            loginUserRoleOpenInfoResult.setPositionFlg(1);

        // ログインユーザemailがTraderUserEmailと一致すれば、相手方とする
        }else if(loginUserRoleOpenInfo.getTraderUserEmail().equals(email)){
            loginUserRoleOpenInfoResult.setPositionFlg(2);

        // ログインユーザemailがMediatorUserEmailと一致すれば、調停人とする
        }else if(loginUserRoleOpenInfo.getMediatorUserEmail().equals(email)){
            loginUserRoleOpenInfoResult.setPositionFlg(3);
        }
        System.out.println(loginUserRoleOpenInfoResult);
        loginUserRoleOpenInfo.setPositionFlg(loginUserRoleOpenInfoResult.getPositionFlg());
        return loginUserRoleOpenInfo;
    }

    @Override
    public List<CaseFileInfo> getCaseFileInfo(String caseId,String id,Integer positionFlg,Integer mediatorDisclosureFlag) {
        List<CaseFileInfo> caseFileInfoList = new ArrayList<>();
        try{
            // 案件添付ファイル取得
            caseFileInfoList = loginUserRoleOpenInfoMapper.selectCaseFileInfoList(caseId, id, positionFlg, mediatorDisclosureFlag);
            
        } catch (Exception e) {
            System.out.println("エラー画面(404)へ遷移");
        } 
        return caseFileInfoList; 
    }
}
