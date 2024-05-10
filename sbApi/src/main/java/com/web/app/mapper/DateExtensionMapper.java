package com.web.app.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DateExtensionMapper {

    Date getCaseInfo(String CaseId, String PlatformId);
    
}
