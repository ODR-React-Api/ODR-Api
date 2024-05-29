package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.MosFileList.Files;

 /** 
 * S07_申立て詳細画面・ファイル
 * API_案件添付ファイル取得
 * Mapper層
 * GetFileInfoMapper
 * 
 * @author DUC 祭卉康
 * @since 2024/05/27
 * @version 1.0
 */
@Mapper
public interface GetFileInfoMapper {

    // id、caseId、flag、mediatorDisclosureFlag による情報の照会
    Files getFileInfo(String id, String caseId, int flag, int mediatorDisclosureFlag);

}
