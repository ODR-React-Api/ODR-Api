package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.CaseFileInfo;
import com.web.app.domain.GetFileInfo;
import com.web.app.mapper.GetFileInfoMapper;
import com.web.app.mapper.GetLoginUserRoleOpenInfoMapper;
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

    // ログインユーザのロールと開示情報取得
    @Autowired
    private GetLoginUserRoleOpenInfoMapper getLoginUserRoleOpenInfoMapper;

    // 案件添付ファイル取得
    @Autowired
    private GetFileInfoMapper getFileInfoMapper;


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
        GetFileInfo getFileInfo = new GetFileInfo();
        // ログインユーザのロールと開示情報取得API
        getFileInfo = getLoginUserRoleOpenInfoMapper.selectLoginUserRoleOpenInfo(caseId);

        // ログインユーザIdがPetitionUserIdと一致すれば、申立人とする
        if (getFileInfo.getPetitionUserId().equals(id)) {
            getFileInfo.setPositionFlg(1);

            // ログインユーザemailがTraderUserEmailと一致すれば、相手方とする
        } else if (getFileInfo.getTraderUserEmail().equals(email)) {
            getFileInfo.setPositionFlg(2);

            // ログインユーザemailがMediatorUserEmailと一致すれば、調停人とする
        } else if (getFileInfo.getMediatorUserEmail().equals(email)) {
            getFileInfo.setPositionFlg(3);
        }
        return getFileInfo;
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
        caseFileInfoList = getFileInfoMapper.selectCaseFileInfoList(caseId, id, positionFlg,
                mediatorDisclosureFlag);

        return caseFileInfoList;
    }
}
