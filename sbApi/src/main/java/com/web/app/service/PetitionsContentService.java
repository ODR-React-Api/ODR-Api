package com.web.app.service;

import com.web.app.domain.PetitionsContent;

public interface PetitionsContentService {

    PetitionsContent selectPetitionData(String caseId);

}
