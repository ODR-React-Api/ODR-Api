package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.controller.QueryDetailCaseController;
import com.web.app.domain.ReturnResult;
import com.web.app.domain.SelectUserInfoForCase;
import com.web.app.mapper.QueryMapper;
import com.web.app.service.QueryService;

@Service
public class QueryServiceImpl implements QueryService{

  @Autowired
  private QueryMapper queryMapper;

  @Autowired
  private QueryDetailCaseController queryDetailCaseController;

  @Override
  @Transactional
  public List<ReturnResult> queryData(String uid, String queryString) {

    List<ReturnResult> results = new ArrayList();

    // ユーザ情報を取得
    String email = queryMapper.getUserInfo(uid);
    System.out.println("email:" + email);

    // ケースの取得
      List<SelectUserInfoForCase> selectUserInfoForCases1 = queryMapper.selectUserInfoForCase1(email);
      System.out.println("selectUserInfoForCase1:" + selectUserInfoForCases1);

      // ケース詳細内容の取得
      for (SelectUserInfoForCase selectUserInfoForCase : selectUserInfoForCases1) {
        ReturnResult queryCase = queryDetailCaseController.querydetailCase(selectUserInfoForCase.getCaseId(),selectUserInfoForCase.getPetitionUserId(),1,queryString);
        queryCase.setPositionFlg(1);

        // ReturnResult queryCase = new ReturnResult();
        // queryCase.setCid("0000000001");
        // queryCase.setCaseTitle("件名");
        // queryCase.setPetitionDate(new Date());
        // queryCase.setCaseStatus("1");
        // queryCase.setCorrespondDate("99999999");
        // queryCase.setMsgCount(2);
        // queryCase.setCorrespondence("1");

        results.add(queryCase);
      }

      List<SelectUserInfoForCase> selectUserInfoForCases2 = queryMapper.selectUserInfoForCase2(email);
      System.out.println("selectUserInfoForCase2:" + selectUserInfoForCases2);

      // ケース詳細内容の取得
      for (SelectUserInfoForCase selectUserInfoForCase : selectUserInfoForCases2) {
        // ReturnResult queryCase = new ReturnResult();
        ReturnResult queryCase = queryDetailCaseController.querydetailCase(selectUserInfoForCase.getCaseId(),selectUserInfoForCase.getPetitionUserId(),2,queryString);
        queryCase.setPositionFlg(2);

        // ReturnResult queryCase = new ReturnResult();
        // queryCase.setCid("0000000001");
        // queryCase.setCaseTitle("件名");
        // queryCase.setPetitionDate(new Date());
        // queryCase.setCaseStatus("1");
        // queryCase.setCorrespondDate("99999999");
        // queryCase.setMsgCount(2);
        // queryCase.setCorrespondence("1");

        results.add(queryCase);
      }

      List<SelectUserInfoForCase> selectUserInfoForCases3 = queryMapper.selectUserInfoForCase3(email);
      System.out.println("selectUserInfoForCase3:" + selectUserInfoForCases3);

      // ケース詳細内容の取得
      for (SelectUserInfoForCase selectUserInfoForCase : selectUserInfoForCases3) {
        ReturnResult queryCase = queryDetailCaseController.querydetailCase(selectUserInfoForCase.getCaseId(),selectUserInfoForCase.getPetitionUserId(),3,queryString);
        queryCase.setPositionFlg(3);

        // ReturnResult queryCase = new ReturnResult();
        // queryCase.setCid("0000000001");
        // queryCase.setCaseTitle("件名");
        // queryCase.setPetitionDate(new Date());
        // queryCase.setCaseStatus("1");
        // queryCase.setCorrespondDate("99999999");
        // queryCase.setMsgCount(2);
        // queryCase.setCorrespondence("1");

        results.add(queryCase);
      }
    return results;
  }
  
}