package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.MosList.DraftSavingDate;
import com.web.app.mapper.GetSaveDataInfoMapper;
import com.web.app.service.GetDraftSavingService;

@Service
public class GetDraftSavingServiceImpl implements GetDraftSavingService {

    @Autowired
    private GetSaveDataInfoMapper getDraftSavingMapper;

    @Override
    public Integer getgetDraftSaving(String uid) {
        DraftSavingDate draftSavingDate = getDraftSavingMapper.getSaveDataInfo(uid);
        if (draftSavingDate != null && requiredItemIsNull(draftSavingDate)) {
            return 1;
        } else {
            return 0;
        }
    }

    private boolean requiredItemIsNull(DraftSavingDate draftSavingDate) {
        if (draftSavingDate.getTraderUserEmail() != null
                && draftSavingDate.getProductName() != null
                && draftSavingDate.getBoughtDate() != null
                && draftSavingDate.getPrice() != null
                && draftSavingDate.getPetitionTypeValue() != null
                && draftSavingDate.getPetitionContext() != null
                && draftSavingDate.getExpectResloveTypeValue() != null) {
            return true;
        } else {
            return false;
        }
    }

}
