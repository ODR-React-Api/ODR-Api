package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.CaseFileInfo;
import com.web.app.domain.GetFileInfo;
import com.web.app.mapper.GetFileInfoMapper;
import com.web.app.service.MosFileListService;

/**
 * S7_申立てファイル一覧画面
 * Service層実現類
 * MosFileListServiceImpl
 * 
 * @author DUC 閆文静
 * @since 2024/04/25
 * @version 1.0
 */
@Service
public class MosFileListServiceImpl implements MosFileListService {

    @Autowired
    private GetFileInfoMapper loginUserRoleOpenInfoMapper;

    /**
     * ログインユーザのロールと開示情報取得
     *
     * @param caseId セッション情報のcaseid
     * @param id     ログインユーザId
     * @param email  ログインユーザemai
     * @return 取得したログインユーザのロールと開示情報
     * @throws Exception ログインユーザのロールと開示情報取得失败
     */
    @Override
    public GetFileInfo getLoginUserRoleOpenInfo(String caseId, String id, String email) {
        GetFileInfo loginUserRoleOpenInfo = new GetFileInfo();
        // ログインユーザのロールと開示情報取得API
        loginUserRoleOpenInfo = loginUserRoleOpenInfoMapper.selectLoginUserRoleOpenInfo(caseId);

        // ログインユーザIdがPetitionUserIdと一致すれば、申立人とする
        if (loginUserRoleOpenInfo.getPetitionUserId().equals(id)) {
            loginUserRoleOpenInfo.setPositionFlg(1);

            // ログインユーザemailがTraderUserEmailと一致すれば、相手方とする
        } else if (loginUserRoleOpenInfo.getTraderUserEmail().equals(email)) {
            loginUserRoleOpenInfo.setPositionFlg(2);

            // ログインユーザemailがMediatorUserEmailと一致すれば、調停人とする
        } else if (loginUserRoleOpenInfo.getMediatorUserEmail().equals(email)) {
            loginUserRoleOpenInfo.setPositionFlg(3);
        }
        System.out.println(loginUserRoleOpenInfo);
        return loginUserRoleOpenInfo;
    }

    /**
     * 案件添付ファイル取得
     *
     * @param caseId                 セッション情報のcaseid
     * @param id                     ログインユーザId
     * @param positionFlg            立場フラグ
     * @param mediatorDisclosureFlag 調停人情報開示フラグ
     * @return 取得した案件添付ファイル
     * @throws Exception エラー画面(404)へ遷移
     */
    @Override
    public List<CaseFileInfo> getCaseFileInfo(String caseId, String id, Integer positionFlg,
            Integer mediatorDisclosureFlag) {
        List<CaseFileInfo> caseFileInfoList = new ArrayList<>();

        // 案件添付ファイル取得
        caseFileInfoList = loginUserRoleOpenInfoMapper.selectCaseFileInfoList(caseId, id, positionFlg,
                mediatorDisclosureFlag);

        return caseFileInfoList;
    }
}
