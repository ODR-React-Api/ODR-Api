package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.answerLogin.GetReplies;
import com.web.app.mapper.GetRepliesMapper;
import com.web.app.service.GetRepliesService;

@Service
public class GetRepliesServiceImpl  implements GetRepliesService {

    @Autowired
    private GetRepliesMapper getRepliesMapper;
    
    @Override
    public List<GetReplies> getReplies(String CaseId,String PlatformId) {
    List<GetReplies> list = new ArrayList<GetReplies>();
    // list = getPreUserDataMapper.getUserPre(guid);
    list = getRepliesMapper.getReplies(CaseId,PlatformId);
    return list;
  }
}
