package com.web.app.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.service.MediationsMakeService;
import com.web.app.controller.List;
import com.web.app.domain.mediationsMake.InsMediationsData;
import com.web.app.mapper.InsMediationsDataMapper;;

@Service
public class MediationsMakeServiceImpl implements MediationsMakeService {
    @Autowired
    private InsMediationsDataMapper mediationcaseMapper;

    @Override
    public List<InsMediationsData> mediationsDataSearch(InsMediationsData mediationcase) {

        List<InsMediationsData> mList = mediationcaseMapper.MediationcaseSearch(mediationcase);

        return mList;
    }

    @Override
    public int MediationcaseInsert(InsMediationsData mediationcase) {

        // id赋予
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().replaceAll("-", "");

        mediationcase.setId(id);

        int MediationcaseInsertStatus = mediationcaseMapper.MediationcaseInsert(mediationcase);

        return MediationcaseInsertStatus;
    }

}