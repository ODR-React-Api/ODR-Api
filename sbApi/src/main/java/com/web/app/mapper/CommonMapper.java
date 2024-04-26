package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.Cases;
import com.web.app.domain.Entity.MailTemplates;
import com.web.app.domain.Entity.MasterPlatforms;
import com.web.app.domain.Entity.MasterTypes;
import com.web.app.domain.Entity.OdrUsers;

@Mapper
public interface CommonMapper {

    // 根据platformID查询platform信息
    MasterPlatforms FindMasterPlatforms(String platformId);

    // 根据用户ID或用户邮箱查询用户信息
    OdrUsers FindUserByUidOrEmail(String uid, String email, String platformId);

    // 根据caseID查询案件信息
    Cases FindCasesInfoByCid(String cid);

    // 查询种类信息
    List<MasterTypes> FindMasterTypeName(String type, String languageId, String platformId);

    // 取得邮件模板
    List<MailTemplates> FindMailTemplatesList(String platformId, String tempId);
}
