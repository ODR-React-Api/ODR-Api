package com.web.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.User;
import com.web.app.domain.Entity.ActionFileRelations;
import com.web.app.domain.Entity.ActionHistories;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.util.DisplayNameAddResult;
import com.web.app.domain.util.DisplayNameResult;
import com.web.app.mapper.CommonMapper;
import com.web.app.service.CommonService;
import com.web.app.service.UtilService;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private UtilService utilService;

    @Autowired
    private CommonMapper commonMapper;

    /**
     * 案件別個人情報リレーションデータ取得(申立人/相手方)
     * 
     * @param identity   true 申立人 false 相手方
     * @param languageId
     * @param platformId プラットフォームID
     * @param caseId     案件ID
     * @return user
     * @throws Exception
     */
    @Override
    public User GetUserDataFromCaseIdentity(Boolean identity, String languageId, String platformId, String caseId) {
        try {

            return commonMapper.GetUserDataFromCaseIdentity(identity, languageId, platformId, caseId);

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * アクション履歴新規登録
     * 
     * @param actionHistories アクション履歴
     * @param fileId          ファイルId
     * @param parametersFlag  Parametersのログインユーザ名があるフラグ 
     * @param displayNameFlag 関係者内容取得するフラグ
     * @return true false
     */
    @Transactional(noRollbackFor = { ArithmeticException.class })
    @Override
    public Boolean InsertActionHistories(ActionHistories actionHistories, List<String> fileId, Boolean parametersFlag,
            Boolean displayNameFlag) {
        // ファイルをアップロードしましたか
        if (fileId == null || fileId.size() == 0) {
            actionHistories.setHaveFile(false);
        } else {
            actionHistories.setHaveFile(true);
        }

        OdrUsers odrUser = new OdrUsers();
        // get userInfo
        odrUser = this.commonMapper.FindUserByUidOrEmail(actionHistories.getUserId(),
                null,
                actionHistories.getPlatformId());

        if (odrUser != null && odrUser.Email != null && parametersFlag) {
            DisplayNameAddResult displayNameAddResult = GetDisplayName(odrUser.LanguageId, actionHistories.PlatformId,
                    odrUser.Email, odrUser.Uid);

            // 関係者内容取得 displayName
            if (!displayNameFlag) {
                displayNameAddResult = GetDisplayFullName(odrUser.LanguageId,
                        actionHistories.PlatformId, odrUser.Email,
                        null);
            }

            if (displayNameAddResult != null && displayNameAddResult.UserName != null) {
                actionHistories.Parameters = displayNameAddResult.UserName;
            }
        }

        actionHistories.id = utilService.GetGuid();
        actionHistories.ActionDateTime = new Date();
        actionHistories.DeleteFlag = false;
        actionHistories.LastModifiedDate = new Date();

        // アクション履歴新規登録
        int result = commonMapper.InsHistories(actionHistories);
        if (result == 0) {
            return false;
        }

        // 「アクション履歴-添付ファイルリレーション」新規登録
        if (actionHistories.HaveFile == true) {
            for (String stringFileId : fileId) {
                ActionFileRelations actionFileRelations = new ActionFileRelations();

                actionFileRelations.Id = utilService.GetGuid();
                actionFileRelations.PlatformId = actionHistories.PlatformId;
                actionFileRelations.CaseId = actionHistories.CaseId;
                actionFileRelations.ActionHistoryId = actionHistories.id;
                actionFileRelations.FileId = stringFileId;
                actionFileRelations.DeleteFlag = false;
                actionFileRelations.LastModifiedDate = new Date();
                actionFileRelations.LastModifiedBy = actionHistories.LastModifiedBy;

                ActionFileRelations actionFileRelationsCheckNull = commonMapper
                        .FindFileRelations(actionFileRelations.Id);

                if (actionFileRelationsCheckNull != null) {
                    return false;
                }

                int actionFileCountRelations = commonMapper.InsertActionFileRelations(actionFileRelations);

                if (actionFileCountRelations != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 関係者内容取得
     * 
     * @param languageId 言語ID
     * @param platformId プラットフォームID
     * @param email      メールアドレス
     * @param uid        ユーザID
     * @return 関係者内容
     */
    private DisplayNameAddResult GetDisplayName(String languageId, String platformId, String email, String uid) {
        DisplayNameAddResult displayNameAddResult = new DisplayNameAddResult();

        if ((email != null || email != "") && (uid != null || uid != "")) {
            return null;
        }
        DisplayNameResult displayNameResult = this.commonMapper.FindDisplayName(platformId, email, uid);
        if (displayNameResult != null) {
            displayNameAddResult.UserName = displayNameResult.LastName + " " + displayNameResult.FirstName;

            displayNameAddResult.UserKanaName = displayNameResult.LastName_kana + " "
                    + displayNameResult.FirstName_kana;

            displayNameAddResult.CompanyName = displayNameResult.CompanyName;

            displayNameAddResult.UserEmail = displayNameResult.Email;

            return displayNameAddResult;
        }
        return null;
    }

    /**
     * 関係者内容取得
     * 
     * @param languageId 言語ID
     * @param platformId プラットフォームID
     * @param email      メールアドレス
     * @param uid        ユーザID
     * @return 関係者内容
     */
    private DisplayNameAddResult GetDisplayFullName(String languageId, String platformId, String email, String uid) {
        DisplayNameAddResult displayNameAddResult = new DisplayNameAddResult();

        DisplayNameResult displayNameResult = this.commonMapper.FindDisplayName(platformId, email, uid);

        if (displayNameResult != null) {
            displayNameAddResult.UserName = displayNameResult.LastName + " " + displayNameResult.FirstName;
            displayNameAddResult.UserKanaName = displayNameResult.LastName_kana + " "
                    + displayNameResult.FirstName_kana;
            displayNameAddResult.CompanyName = displayNameResult.CompanyName;

            displayNameAddResult.UserEmail = displayNameResult.Email;
            return displayNameAddResult;
        }

        return null;
    }

}
