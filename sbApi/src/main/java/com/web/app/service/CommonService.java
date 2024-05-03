package com.web.app.service;

import com.web.app.domain.User;
import com.web.app.domain.Entity.ActionHistories;

public interface CommonService {
    User GetUserDataFromCaseIdentity(Boolean identity, String languageId, String platformId, String caseId);

    Integer InsHistories(ActionHistories actionHistories);

}
