package com.web.app.service;

import com.web.app.domain.WithdrawalReturn;

public interface WithdrawalService {

  WithdrawalReturn withdrawal(String caseId);
  
}
