package com.web.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.mapper.DateExtensionMapper;
import com.web.app.service.DateExtensionService;

@Service
public class DateExtensionServiceImpl implements DateExtensionService {

    @Autowired
    private DateExtensionMapper dateExtensionMapper;

    @Override
    public Date getCaseInfo(String CaseId, String PlatformId) {
        return dateExtensionMapper.getCaseInfo(CaseId, PlatformId);
    }
}
