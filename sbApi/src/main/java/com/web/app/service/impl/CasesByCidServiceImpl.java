package com.web.app.service.impl;

import com.web.app.domain.couAnswerLogin.CasesByCid;
import com.web.app.mapper.CasesByCidMapper;
import com.web.app.service.CasesByCidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CasesByCidServiceImpl  implements CasesByCidService {

    @Autowired
    private CasesByCidMapper casesByCidMapper;
    
    @Override
    public List<CasesByCid> casesByCid(String CaseId,String PlatformId) {
    List<CasesByCid> list = new ArrayList<CasesByCid>();
    list = casesByCidMapper.casesByCid(CaseId,PlatformId);
    return list;
}
}
