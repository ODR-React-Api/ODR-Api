package com.web.app.service;

import com.web.app.domain.ParticipatedStatusChangeResultInfo;

public interface ParticipatedStatusChangeService {
    ParticipatedStatusChangeResultInfo ParticipatedStatusChangeInfoSearch(String caseId);

}
