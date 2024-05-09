package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.AttachedFile;
import com.web.app.domain.CasePetitions;
import com.web.app.domain.ExtensionItem;

@Mapper
public interface PetitionsContentMapper {

  CasePetitions PetitionListDataSearch(String caseId);

  List<AttachedFile> PetitionFileSearch(String caseId);

  List<ExtensionItem> PetitionExtensionitemSearch(String caseId);
}
