package com.web.app.service;

import com.web.app.domain.SelectCondition;
import com.web.app.domain.ReturnResult;

public interface SearchDetailCaseService {

  ReturnResult searchSetailCase(SelectCondition searchCase);
}
