package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.InsertFileRelationsData;
import com.web.app.domain.InsertFilesData;
import com.web.app.domain.InsertRepliesData;
import com.web.app.mapper.UpdRepliesDataMapper;
import com.web.app.service.UpdRepliesDataService;
import com.web.app.service.UtilService;

@Service
public class UpdRepliesDataServiceImpl implements UpdRepliesDataService{

    @Autowired
    UpdRepliesDataMapper updRepliesDataMapper;
    UtilService utilService;

    @Override
    public String updateRepliesData(String platFormId,String caseId,String loginUser,InsertRepliesData insertRepliesData) {
        String data;
        data = updRepliesDataMapper.selectData(platFormId, caseId);
        if (data == null) {
            insertRepliesData.setId(utilService.GetGuid());
            insertRepliesData.setPlatformId(platFormId);
            insertRepliesData.setCaseId(caseId);
            insertRepliesData.setStatus(0);
            insertRepliesData.setDeleteFlag(0);
            insertRepliesData.setLastModifiedBy(loginUser);
            updRepliesDataMapper.insertReplies(insertRepliesData);
            return "登陆";
        }else{
            return "更新";
        }
    }

    

    @Override
    public String selectData(String platFormId, String caseId) {
        return updRepliesDataMapper.selectData(platFormId, caseId);
    }

    @Override
    public int insertReplies(InsertRepliesData insertRepliesData) {
        
        return updRepliesDataMapper.insertReplies(insertRepliesData);
    }

    @Override
    public int insertFiles(InsertFilesData insertFilesData) {
        
        return updRepliesDataMapper.insertFiles(insertFilesData);
    }

    @Override
    public int insertFileRelations(InsertFileRelationsData insertFileRelationsData) {
        
        return updRepliesDataMapper.updateFileRelations(insertFileRelationsData);
    }

    @Override
    public int updateReplies(InsertRepliesData insertRepliesData) {
        
        return updRepliesDataMapper.updateReplies(insertRepliesData);
    }

    @Override
    public int updateFiles(InsertFilesData insertFilesData) {
        
        return updRepliesDataMapper.updateFiles(insertFilesData);
    }

    @Override
    public int updateFileRelations(InsertFileRelationsData insertFileRelationsData) {
        
        return updRepliesDataMapper.updateFileRelations(insertFileRelationsData);
    }


    
}
