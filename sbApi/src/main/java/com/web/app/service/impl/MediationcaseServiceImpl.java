package com.web.app.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.service.MediationcaseService;
import com.web.app.controller.List;
import com.web.app.domain.Mediationcase;
import com.web.app.mapper.MediationcaseMapper;;

@Service
public class MediationcaseServiceImpl implements MediationcaseService {
    @Autowired
    private MediationcaseMapper mediationcaseMapper;

    @Override
    public int MediationcaseInsert(Mediationcase mediationcase) {

        // id赋予
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().replaceAll("-","");

        mediationcase.setId(id);

        int MediationcaseInsertStatus = mediationcaseMapper.MediationcaseInsert(mediationcase);

        return MediationcaseInsertStatus;
    }

    @Override
    public List<Mediationcase> MediationcaseSearch(Mediationcase mediationcase) {

        List<Mediationcase> mList =mediationcaseMapper.MediationcaseSearch(mediationcase);
        
        return mList;
}

}