package com.web.app.mapper;

import com.web.app.domain.InsertFileRelationsData;
import com.web.app.domain.InsertFilesData;
import com.web.app.domain.InsertRepliesData;

public interface UpdRepliesDataMapper {
    String selectData(String platFormId,String caseId);
    
    int insertReplies(InsertRepliesData insertRepliesData);

    int insertFiles(InsertFilesData insertFilesData);

    int insertFileRelations(InsertFileRelationsData insertFileRelationsData);

    int updateReplies(InsertRepliesData insertRepliesData);

    int updateFiles(InsertFilesData insertFilesData);

    int updateFileRelations(InsertFileRelationsData insertFileRelationsData);

}
