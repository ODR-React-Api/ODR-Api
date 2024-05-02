package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.GetPreUserData;
import com.web.app.mapper.GetPreUserDataMapper;
import com.web.app.service.GetPreUserDataService;

@Service
public class GetPreUserDataServiceImpl implements GetPreUserDataService {
  @Autowired
  private GetPreUserDataMapper getPreUserDataMapper;

  @Override
  public List<GetPreUserData> getUserPre(String guid) {
    List<GetPreUserData> list = new ArrayList<GetPreUserData>();
    // list = getPreUserDataMapper.getUserPre(guid);
    list = getPreUserDataMapper.getUserPre(guid);
    return list;
  }
}