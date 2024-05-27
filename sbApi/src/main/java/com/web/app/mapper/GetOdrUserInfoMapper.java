package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.MedUserConfirm.OdrUsers;

@Mapper
public interface GetOdrUserInfoMapper {
    CaseRelations getMediatorUserEmail(String caseId);

    OdrUsers getOdrUserInfo(String mediatorUserEmail, String languageId);

}
