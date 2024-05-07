package com.web.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.MediatorDisclosureRequest;
import com.web.app.mapper.MediatorDisclosureMapper;
import com.web.app.service.MediatorDisclosureService;

@Service
public class MediatorDisclosureServiceImpl implements MediatorDisclosureService {
    @Autowired
    private MediatorDisclosureMapper mediatorDisclosureMapper;

    @Override
    @Transactional(noRollbackFor = { ArithmeticException.class }) // 设置当出现ArithmeticException时，不回滚
    public Boolean updMediatorDisclosureFlag(MediatorDisclosureRequest mediatorDisclosureRequest) {
        try {
            mediatorDisclosureRequest.setLastModifiedDate(new Date());
            Boolean flg = mediatorDisclosureMapper.updMediatorDisclosureFlag(mediatorDisclosureRequest);
            // if (flg) {
            // // SendMailRequest
            // // utilService.SendMail(null);
            // }
            return flg;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    // @Transactional(noRollbackFor = { ArithmeticException.class }) //
    // 设置当出现ArithmeticException时，不回滚
    public Date getMediatorDisclosureDate(String caseId) {
        return mediatorDisclosureMapper.getMediatorDisclosureDate(caseId);
    }
}
