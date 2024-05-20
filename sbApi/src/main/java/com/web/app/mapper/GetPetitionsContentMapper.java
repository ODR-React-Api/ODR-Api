package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CasePetitions;
import com.web.app.domain.MosDetail.AttachedFile;
import com.web.app.domain.MosDetail.ExtensionItem;

/**
 * 申立ての内容取得Mapper
 * 
 * @author DUC 王亞テイ
 * @since 2024/04/23
 * @version 1.0
 */
@Mapper
public interface GetPetitionsContentMapper {

    // 申立ての内容取得
    CasePetitions petitionListDataSearch(String caseId);

    // 添付資料取得
    List<AttachedFile> petitionFileSearch(String caseId);

    // 拡張項目取得
    List<ExtensionItem> petitionExtensionitemSearch(String caseId);
}
