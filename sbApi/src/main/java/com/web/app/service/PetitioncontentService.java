package com.web.app.service;

import com.web.app.domain.Petitioncontent;

public interface PetitioncontentService {

  Petitioncontent selectPetitionData(String caseId);
  
}
