package com.web.app.service;

import java.util.List;

import com.web.app.domain.User;
import com.web.app.domain.Entity.ActionHistories;

public interface CommonService {

    User GetUserDataFromCaseIdentity(Boolean identity, String languageId, String platformId, String caseId);

    Boolean InsertActionHistories(ActionHistories actionHistories, List<String> fileId, Boolean parametersFlag,
            Boolean displayNameFlag);

}
