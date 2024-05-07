package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
