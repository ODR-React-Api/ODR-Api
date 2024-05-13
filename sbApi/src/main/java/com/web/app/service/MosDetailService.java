package com.web.app.service;

import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.MosDetail.PetitionsContent;
import com.web.app.domain.MosDetail.RelationsContent;

public interface MosDetailService {

    PetitionsContent selectPetitionData(String caseId);

    CaseRelations selectRelationsData(String caseId);

    RelationsContent selectRelationsContentData(String caseId);

    int updateMediatorHistoriesData(String caseId, String uid,String platformId,String messageGroupId);

}
