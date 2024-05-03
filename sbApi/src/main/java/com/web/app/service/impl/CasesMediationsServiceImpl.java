package com.web.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.MediatorDisclosureRequest;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.mapper.CasesMediationsMapper;
import com.web.app.service.CasesMediationsService;
import com.web.app.service.UtilService;

@Service
public class CasesMediationsServiceImpl implements CasesMediationsService {
  @Autowired
  private CasesMediationsMapper casesMediationsMapper;

  @Autowired
  private UtilService utilService;

  @Override
  @Transactional(noRollbackFor = { ArithmeticException.class }) // 设置当出现ArithmeticException时，不回滚
  public Boolean delAboutCasesMediations(String caseId) {
    try {
      return casesMediationsMapper.delAboutCasesMediations(caseId);

    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  @Transactional(noRollbackFor = { ArithmeticException.class }) // 设置当出现ArithmeticException时，不回滚
  public Boolean updMediatorDisclosureFlag(MediatorDisclosureRequest mediatorDisclosureRequest) {
    try {
      mediatorDisclosureRequest.setLastModifiedDate(new Date());
      Boolean flg = casesMediationsMapper.updMediatorDisclosureFlag(mediatorDisclosureRequest);
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
    return casesMediationsMapper.getMediatorDisclosureDate(caseId);
  }
}
