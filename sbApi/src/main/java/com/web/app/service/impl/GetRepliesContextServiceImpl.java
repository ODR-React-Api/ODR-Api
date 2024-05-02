package com.web.app.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.web.app.controller.GetRepliesContextController;
import com.web.app.domain.GetReplies;
import com.web.app.domain.GetRepliesContext;
import com.web.app.mapper.GetRepliesContextMapper;
import com.web.app.service.GetRepliesContextService;

@Service
public class GetRepliesContextServiceImpl implements GetRepliesContextService {
    @Autowired
    private GetRepliesContextMapper getRepliesContextMapper;
    @Override
    public List<GetRepliesContext> getRepliesContext(String CaseId,String PlatformId) {
    List<GetRepliesContext> list = new ArrayList<GetRepliesContext>();
    list = getRepliesContextMapper.getRepliesContext(CaseId,PlatformId);
    return list;

}
}

// @Service
// public class GetRepliesServiceImpl  implements GetRepliesService {

//     @Autowired
//     private GetRepliesMapper getRepliesMapper;
    
//     @Override
//     public List<GetReplies> getReplies(String CaseId,String PlatformId) {
//     List<GetReplies> list = new ArrayList<GetReplies>();
//     // list = getPreUserDataMapper.getUserPre(guid);
//     list = getRepliesMapper.getReplies(CaseId,PlatformId);
//     return list;
//   }
// }
