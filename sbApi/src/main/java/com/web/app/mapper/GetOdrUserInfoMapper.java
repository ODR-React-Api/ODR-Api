package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.MedUserConfirm.OdrUsers;

/**
 * 調停人ユーザ情報取得
 * 
 * @author DUC 馬芹
 * @since 2024/05/10
 * @version 1.0
 */
@Mapper
public interface GetOdrUserInfoMapper {
    // 調停人メール取得
    CaseRelations getMediatorUserEmail(String caseId);
    // ユーザ情報取得
    OdrUsers getOdrUserInfo(String mediatorUserEmail, String languageId);

}
