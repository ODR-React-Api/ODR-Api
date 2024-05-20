package com.web.app.service;

import java.util.List;

import com.web.app.domain.User;
import com.web.app.domain.Entity.ActionHistories;

public interface CommonService {
    /**
     * 
     * @param identity
     * @param languageId
     * @param platformId
     * @param caseId
     * @return
     */
    User GetUserDataFromCaseIdentity(Boolean identity, String languageId, String platformId, String caseId);

    /**
     * アクション履歴新規登録
     * 
     * @param actionHistories アクション履歴
     * @param fileId          ファイルId
     * @param parametersFlag  Parametersのログインユーザ名があるフラグ
     * @param displayNameFlag 関係者内容取得するフラグ
     * @return true false
     */
    Boolean InsHistories(ActionHistories actionHistories, List<String> fileId, Boolean parametersFlag,
            Boolean displayNameFlag);

}
