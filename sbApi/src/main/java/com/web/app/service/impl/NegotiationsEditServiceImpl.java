package com.web.app.service.impl;

import com.web.app.domain.CaseFileRelations;
import com.web.app.domain.Files;
import com.web.app.domain.NegotiationsEdit;
import com.web.app.mapper.NegotiationsEditMapper;
import com.web.app.service.NegotiationsEditService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
public class NegotiationsEditServiceImpl implements NegotiationsEditService {

  @Autowired
  private NegotiationsEditMapper negotiationsEditMapper;

  public List<Integer> selectStatusList(NegotiationsEdit negotiationsEdit) {

    List<Integer> status = negotiationsEditMapper.selectStatusList(negotiationsEdit.getCaseId(),
        negotiationsEdit.getPlatformId(), negotiationsEdit.getDeleteFlag());

    return status;

  }

  @Transactional
  @Override
  public int addNegotiationsEdit(NegotiationsEdit negotiationsEdit, Files files, CaseFileRelations caseFileRelations) {
    int result = negotiationsEditMapper.insertNegotiationsEdit(negotiationsEdit);
    if (result == 0) {
      return 0;
    }
    //「添付ファイル」の新規登録
    int result2 = negotiationsEditMapper.insertFiles(files);
    if (result2 == 0) {
      return 0;
    }
    //「案件-添付ファイルリレーション」新規登録
    int result3 = negotiationsEditMapper.insertCaseFileRelations(caseFileRelations);
    if (result3 == 0) {
      return 0;
    }

    return 1;

  }
}
