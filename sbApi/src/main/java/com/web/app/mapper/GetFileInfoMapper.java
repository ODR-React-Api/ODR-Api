package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MosFileList.Files;

 /**
 * 工具类Mapper
 * 
 * @author DUC 祭卉康
 * @since 2024/05/27
 * @version 1.0
 */

@Mapper
public interface GetFileInfoMapper {

    // 通过id和caseid查询信息
    Files findGetFileInfo(String id, String caseid, int flag, int mediatorDisclosureFlag);

}
