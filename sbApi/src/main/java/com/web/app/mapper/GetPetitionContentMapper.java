package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CasePetitions;
import com.web.app.domain.MosDetail.AttachedFile;
import com.web.app.domain.MosDetail.ExtensionItem;

@Mapper
public interface GetPetitionContentMapper {

  CasePetitions PetitionListDataSearch(String caseId);

  List<AttachedFile> PetitionFileSearch(String caseId);

  List<ExtensionItem> PetitionExtensionitemSearch(String caseId);
}
