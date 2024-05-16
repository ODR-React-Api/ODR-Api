package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.GetFileInfo;

/**
 * API_ ログインユーザのロールと開示情報取得
 * 
 * @author DUC 閆文静
 * @since 2024/04/25
 * @version 1.0
 */
@Mapper
public interface GetLoginUserRoleOpenInfoMapper {
    // ログインユーザのロールと開示情報取得
    GetFileInfo selectLoginUserRoleOpenInfo(String caseId);
}
