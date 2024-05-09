package com.web.app.service;

import com.web.app.domain.ReturnResult;
import com.web.app.domain.SelectCondition;

public interface SearchDetailCaseService {

    public ReturnResult searchSetailCase(SelectCondition searchCase);
}
