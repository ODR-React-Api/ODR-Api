package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.MedUserChange.InsertFileInfo;
import com.web.app.mapper.MedUserChangeMapper;
import com.web.app.service.MedUserChangeService;
import com.web.app.service.UtilService;

@Service
public class MedUserChangeServiceImpl implements MedUserChangeService {


    @Autowired
    private UtilService utilService;

    @Autowired
    private MedUserChangeMapper medUserChangeMapper;

    @Transactional
    @Override
    public int insertFileInfo(InsertFileInfo insertFileInfo) {
        insertFileInfo.setFileId(utilService.GetGuid());
        insertFileInfo.setCaseFileRelationsId(utilService.GetGuid());

        int fileInsertNum = medUserChangeMapper.insertFile(insertFileInfo);
        int caseFileRelationsInsertNum = medUserChangeMapper.insertCaseFileRelations(insertFileInfo);
        if(fileInsertNum == 1 && caseFileRelationsInsertNum == 1) {
            return 1;
        }
        return 0;
    }
}
