package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.CaseFileRelations;
import com.web.app.domain.Files;
import com.web.app.domain.NegotiationsEdit;

import java.util.List;

/**
 * @author HHH
 * @description 和解案
 * @createDate 2023-01-31 20:38:15
 * @Entity com.web.app.domain.NegotiationsEdit
 */
@Mapper
public interface NegotiationsEditMapper {

  List<Integer> selectStatusList(String caseId, String platformId, int deleteFlag);

  int insertNegotiationsEdit(NegotiationsEdit negotiationsEdit);

  int insertFiles(Files files);

  int insertCaseFileRelations(CaseFileRelations caseFileRelations);
}
