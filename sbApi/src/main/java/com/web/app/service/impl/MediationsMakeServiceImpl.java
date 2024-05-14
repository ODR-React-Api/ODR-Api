package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.service.MediationsMakeService;
import com.web.app.domain.mediationsMake.InsMediationsData;
import com.web.app.mapper.InsMediationsDataMapper;;

@Service
public class MediationsMakeServiceImpl implements MediationsMakeService {
    @Autowired
    private InsMediationsDataMapper mediationcaseMapper;

    @Override
    public ArrayList<InsMediationsData> dataSearch(InsMediationsData mediationcase) {

        ArrayList<InsMediationsData> mList = mediationcaseMapper.dataSearch(mediationcase);

        return mList;
    }

    @Override
    public InsMediationsData mediationDataCount(InsMediationsData mediationcase) {

        InsMediationsData mediationCount = mediationcaseMapper.mediationCount(mediationcase);

        return mediationCount;
    }

    @Transactional
    @Override
    public int insMediationsData2(InsMediationsData mediationcase) {

        // id赋予
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().replaceAll("-", "");

        mediationcase.setId(id);

        int MediationcaseInsertStatus = mediationcaseMapper.insMediationsData2(mediationcase);

        return MediationcaseInsertStatus;
    }

}