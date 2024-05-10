package com.web.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.MediatorDisclosureRequest;
import com.web.app.mapper.InfoPublicSeigenMapper;
import com.web.app.service.InfoPublicSeigenService;

@Service
public class InfoPublicSeigenServiceImpl implements InfoPublicSeigenService {
    @Autowired
    private InfoPublicSeigenMapper infoPublicSeigenMapper;

    @Override
    @Transactional(noRollbackFor = { ArithmeticException.class }) // 设置当出现ArithmeticException时，不回滚
    public Boolean updMediatorDisclosureFlag(MediatorDisclosureRequest mediatorDisclosureRequest) {
        try {
            mediatorDisclosureRequest.setLastModifiedDate(new Date());
            Boolean flg = infoPublicSeigenMapper.updMediatorDisclosureFlag(mediatorDisclosureRequest);
            return flg;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Date getMediatorDisclosureDate(String caseId) {
        return infoPublicSeigenMapper.getMediatorDisclosureDate(caseId);
    }
}
