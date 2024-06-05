package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.Entity.Cases;
import com.web.app.mapper.GetToCaseInfoMapper;
import com.web.app.service.DateExtensionService;
/**
 * S26期日延長画面
 * Service層実現類
 * DateExtensionServiceImpl
 * 
 * @author DUC 田壮飞
 * @since 2024/06/04
 * @version 1.0
 */
@Service
public class DateExtensionServiceImpl implements DateExtensionService{

    @Autowired
    GetToCaseInfoMapper getToCaseInfoMapper;

    @Override
    public Cases GetCaseInfo(String caseId, String platformId) {
        Cases cases=getToCaseInfoMapper.FindCaseInfo(caseId,platformId);
        return cases;
    }

}
