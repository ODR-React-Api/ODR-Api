package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.CasePetitions;
import com.web.app.domain.Petitioncontent;
import com.web.app.mapper.PetitioncontentMapper;
import com.web.app.service.PetitioncontentService;

@Service
public class PetitioncontentServiceImpl implements PetitioncontentService {

  @Autowired
  private PetitioncontentMapper petitioncontentMapper;

  @Override
  public Petitioncontent selectPetitionData(String caseId) {

    Petitioncontent petitioncontent = new Petitioncontent();

    CasePetitions casePetitions = petitioncontentMapper.PetitionListDataSearch(caseId);

    petitioncontent.setCasePetitions(casePetitions);

    petitioncontent.setAttachedFile(petitioncontentMapper.PetitionFileSearch(casePetitions.getCaseId()));

    petitioncontent.setExtensionItem(petitioncontentMapper.PetitionExtensionitemSearch(caseId));

    return petitioncontent;
  }

}
