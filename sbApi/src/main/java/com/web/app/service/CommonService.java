package com.web.app.service;

import java.util.List;

import com.web.app.domain.User;
import com.web.app.domain.Entity.ActionHistories;

/**
 * Service層
 * CommonService
 * 
 * @author DUC 李健
 * @since 2024/05/06
 * @version 1.0
 */
public interface CommonService {

    User GetUserDataFromCaseIdentity(Boolean identity, String languageId, String platformId, String caseId);

    Boolean InsertActionHistories(ActionHistories actionHistories, List<String> fileId, Boolean parametersFlag,
            Boolean displayNameFlag);

}
