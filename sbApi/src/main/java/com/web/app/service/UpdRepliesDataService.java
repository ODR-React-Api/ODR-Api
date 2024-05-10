package com.web.app.service;

import com.web.app.domain.InsertFileRelationsData;
import com.web.app.domain.InsertFilesData;
import com.web.app.domain.InsertRepliesData;

public interface UpdRepliesDataService {

    String updateRepliesData(String platFormId,String caseId,String loginUser,InsertRepliesData insertRepliesData);

    String selectData(String platFormId,String caseId);

    int insertReplies(InsertRepliesData insertRepliesData);

    int insertFiles(InsertFilesData insertFilesData);

    int insertFileRelations(InsertFileRelationsData insertFileRelationsData);

    int updateReplies(InsertRepliesData insertRepliesData);

    int updateFiles(InsertFilesData insertFilesData);

    int updateFileRelations(InsertFileRelationsData insertFileRelationsData);
}
