package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.MosFileList.LoginUserRoleOpenInfo;

/**
 * S07_申立て詳細画面・ファイル
 * API_ログインユーザのロールと開示情報取得
 * Mapper層
 * GetLoginUserRoleOpenInfoMapper
 * 
 * @author DUC 祭卉康
 * @since 2024/05/20
 * @version 1.0
 */
@Mapper
public interface GetLoginUserRoleOpenInfoMapper {

    // caseId による情報の照会
    LoginUserRoleOpenInfo getLoginUserRoleOpenInfo(String caseId);

}
