package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.Entity.CaseRelations;
import com.web.app.mapper.MosDetailMapper;
import com.web.app.service.MosDetailService;

@Service
public class MosDetailServiceImpl implements MosDetailService {

    @Autowired
    private MosDetailMapper mosDetailMapper;
    @Override
    public CaseRelations selectCaseRelationsByCaseId(String CaseId) {
        return mosDetailMapper.selectCaseRelationsByCaseId(CaseId);
    }

}
