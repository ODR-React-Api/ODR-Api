package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.DraftSavingDate;
import com.web.app.domain.DraftSavingReturn;
import com.web.app.mapper.GetDraftSavingMapper;
import com.web.app.service.GetDraftSavingService;

@Service
public class GetDraftSavingServiceImpl implements GetDraftSavingService{

  @Autowired
  private GetDraftSavingMapper getDraftSavingMapper;

  @Override
  public DraftSavingReturn getgetDraftSaving(String uid) {
    DraftSavingReturn draftSavingReturn = new DraftSavingReturn();
    DraftSavingDate draftSavingDate = getDraftSavingMapper.getDraftSavingData(uid);
    draftSavingReturn.setDraftSavingDate(draftSavingDate);
    if(draftSavingDate != null && requiredItemIsNull(draftSavingDate)){
      draftSavingReturn.setDraftSavingFlag(1);
    } else {
      draftSavingReturn.setDraftSavingFlag(0);
    }

    return draftSavingReturn;
  }

  private boolean requiredItemIsNull(DraftSavingDate draftSavingDate){
    if(draftSavingDate.getTraderUserEmail() != null
    && draftSavingDate.getProductName() != null
    && draftSavingDate.getBoughtDate() != null
    && draftSavingDate.getPrice() != null
    && draftSavingDate.getPetitionTypeValue() != null
    && draftSavingDate.getPetitionContext() != null
    && draftSavingDate.getExpectResloveTypeValue() != null){
      return true;
    } else {
      return false;
    }
  }
  
}
