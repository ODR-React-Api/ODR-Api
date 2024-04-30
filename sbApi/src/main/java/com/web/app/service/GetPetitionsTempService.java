package com.web.app.service;

import com.web.app.domain.GetPetitionTemp;
import com.web.app.domain.SessionInfo;

public interface GetPetitionsTempService {
    GetPetitionTemp petitionsTempSearch(SessionInfo sessionInfo);
}
