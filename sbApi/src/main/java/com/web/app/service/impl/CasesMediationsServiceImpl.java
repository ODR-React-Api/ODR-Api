package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.Entity.Cases;
import com.web.app.mapper.CasesMediationsMapper;
import com.web.app.service.CasesMediationsService;

@Service
public class CasesMediationsServiceImpl implements CasesMediationsService {
    @Autowired
    private CasesMediationsMapper casesMediationsMapper;

    @Override
    @Transactional(noRollbackFor = { ArithmeticException.class }) // 设置当出现ArithmeticException时，不回滚
    public Boolean delAboutCasesMediations(String caseId) {
        try {
            return casesMediationsMapper.delAboutCasesMediations(caseId);

        } catch (Exception e) {
            throw e;
        }
    }

    @SuppressWarnings("unlikely-arg-type")
    @Override
    @Transactional(noRollbackFor = { ArithmeticException.class }) // 设置当出现ArithmeticException时，不回滚
    public Boolean updAboutCasesInfo(String caseId, String userType, Boolean withReason) {
        try {
            Cases info = new Cases();
            info.setCid(caseId);
            Cases count = casesMediationsMapper.getMediatorChangeableCount(caseId);
            if (userType.equals("1")) {
                info.setMediatorChangeableCount1(count.getMediatorChangeableCount1() + 1);
            }
            if (userType.equals('2')) {
                info.setMediatorChangeableCount2(count.getMediatorChangeableCount2() + 1);
            }
            return casesMediationsMapper.updAboutCasesInfo(info, withReason);
        } catch (Exception e) {
            throw e;
        }
    }
}
