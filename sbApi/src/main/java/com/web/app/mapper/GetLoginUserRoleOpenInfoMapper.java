package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MosFileList.UserIdentity;

/**
 * 工具类Mapper
 * 
 * @author DUC 祭卉康
 * @since 2024/05/20
 * @version 1.0
 */

@Mapper
public interface GetLoginUserRoleOpenInfoMapper {

    // 通过caseid查询信息
    UserIdentity findGetLoginUserRoleOpenInfo(String caseid);

}
