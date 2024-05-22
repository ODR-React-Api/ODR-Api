package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.Cases;
import com.web.app.domain.Entity.MailTemplates;
import com.web.app.domain.Entity.MasterPlatforms;
import com.web.app.domain.Entity.MasterTypes;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.Entity.CasesByCid;

/**
 * 工具類Mapper
 * 
 * @author DUC 耿浩哲
 * @since 2024/04/17
 * @version 1.0
 */
@Mapper
public interface CommonMapper {

    // platformIDによるplatform情報の照会
    MasterPlatforms FindMasterPlatforms(String platformId);

    // ユーザーIDまたはユーザーメールボックスに基づいたユーザー情報の照会
    OdrUsers FindUserByUidOrEmail(String uid, String email, String platformId);

    // caseIDによる案件情報の照会
    Cases FindCasesInfoByCid(String cid);

    // チェックボックスを取得する方法
    List<MasterTypes> FindMasterTypeName(String type, String languageId, String platformId);

    // メールテンプレートの取得
    List<MailTemplates> FindMailTemplatesList(String platformId, String tempId);

    //API_反訴・回答データ取得
    List<CasesByCid> casesByCid(String caseId,String platformId);

}
