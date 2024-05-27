package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.MedUserConfirm.OdrUsers;

@Mapper
public interface GetOdrUserInfoMapper {
    // 調停人(メール)取得
    CaseRelations getMediatorUserEmail(String caseId);
    // ユーザ情報取得
    OdrUsers getOdrUserInfo(String mediatorUserEmail, String languageId);

}
