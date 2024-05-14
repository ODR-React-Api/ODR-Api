package com.web.app.service;

import java.util.List;

import com.web.app.domain.MosList.ReturnResult;

public interface GetQueryListInfoService {

  List<ReturnResult> queryData(String uid, String queryString);
  
}
