package com.web.app.service;

import java.util.List;

import com.web.app.domain.PetitionsData;

public interface GetPetitionsDataService {
    List<PetitionsData> getPetitionData(String caseId, String plateFormId);
}
