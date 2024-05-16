package com.web.app.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

/**
 * 期日延長画面Mapper
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/02
 * @version 1.0
 */
@Mapper
public interface GetToCaseInfoMapper {

    Date getCaseInfo(String CaseId, String PlatformId);

}
