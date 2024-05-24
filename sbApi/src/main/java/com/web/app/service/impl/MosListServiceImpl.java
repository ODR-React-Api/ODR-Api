package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import com.web.app.domain.MosDetail.CaseDetails;
import com.web.app.domain.MosDetail.ReturnResult;
import com.web.app.domain.MosDetail.UserCase;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.GetListInfoMapper;
import com.web.app.service.MosListService;

/**
 * 一覧取得
 * 
 * @author DUC 王魯興
 * @since 2024/05/28
 * @version 1.0
 */
@Service
public class MosListServiceImpl implements MosListService {

  @Autowired
  private GetListInfoMapper getListInfoMapper;

  @Override
  public List<ReturnResult> getListInfo(String uid) {

    // 戻り値初期化
    List<ReturnResult> returnResultList = new ArrayList<>();
    // ソート後の戻り値の初期化
    List<ReturnResult> returnResultListSort = new ArrayList<>();

    // ユーザ情報を取得する
    String email = getListInfoMapper.selectEmailOdrUsers(uid);

    // ユーザが申立人の場合
    List<UserCase> caseIdPetition1 = getListInfoMapper.selectCaseIdPetition1(email);

    // 调用API「ケース詳細取得」参数初始化
    CaseDetails caseDetails1 = new CaseDetails();
    // 立場フラグ=1
    caseDetails1.setFlag(Constants.STR_CASE_STAGE_REMOVE);
    // セッション.ユーザID
    caseDetails1.setUid(uid);
    // ユーザ情報
    if (caseIdPetition1.size() > Constants.STR_CASE_STAGE_REPLY) {
      for (int i = 0; i < caseIdPetition1.size(); i++) {
        // ①取得したCaseId
        caseDetails1.setCaseId(caseIdPetition1.get(i).getCaseId());
        // ①取得したPetitionUserId
        caseDetails1.setPetitionUserId(caseIdPetition1.get(i).getPetitionUserId());
        // API「ケース詳細取得」を呼び出す。
        // TODO
        ReturnResult returnResult1 = new ReturnResult();
        // 詳細内容の取得
        if (returnResult1.getCid() != null) {
          // 最終戻り値設定
          returnResultList.add(returnResult1);
        }
      }
    }

    // ユーザが相手方の場合
    List<UserCase> caseIdPetition2 = getListInfoMapper.selectCaseIdPetition2(email);

    // API「ケース詳細取得」パラメータ初期化
    CaseDetails caseDetails2 = new CaseDetails();
    // 立場フラグ=2
    caseDetails2.setFlag(Constants.STR_CASE_STAGE_CLAIMREPLY);
    // セッション.ユーザID
    caseDetails2.setUid(uid);
    // ユーザ情報
    if (caseIdPetition2.size() > Constants.STR_CASE_STAGE_REPLY) {
      for (int i = 0; i < caseIdPetition2.size(); i++) {
        // ①取得したCaseId
        caseDetails2.setCaseId(caseIdPetition2.get(i).getCaseId());
        // ①取得したPetitionUserId
        caseDetails2.setPetitionUserId(caseIdPetition2.get(i).getPetitionUserId());
        // API「ケース詳細取得」を呼び出す。
        // TODO
        ReturnResult returnResult2 = new ReturnResult();
        // 詳細内容の取得
        if (returnResult2.getCid() != null) {
          // 最終戻り値設定
          returnResultList.add(returnResult2);
        }
      }
    }

    // ユーザが調停人の場合
    List<UserCase> caseIdPetition3 = getListInfoMapper.selectCaseIdPetition3(email);

    // 调用API「ケース詳細取得」参数初始化
    CaseDetails caseDetails3 = new CaseDetails();
    // 立場フラグ=3
    caseDetails3.setFlag(Constants.STR_CASE_STAGE_NEGOTIATION);
    // セッション.ユーザID
    caseDetails3.setUid(uid);
    // ユーザ情報
    if (caseIdPetition3.size() > Constants.STR_CASE_STAGE_REPLY) {
      for (int i = 0; i < caseIdPetition3.size(); i++) {
        // ①取得したCaseId
        caseDetails3.setCaseId(caseIdPetition3.get(i).getCaseId());
        // ①取得したPetitionUserId
        caseDetails3.setPetitionUserId(caseIdPetition3.get(i).getPetitionUserId());
        // API「ケース詳細取得」を呼び出す。
        // TODO
        ReturnResult returnResult3 = new ReturnResult();
        // 詳細内容の取得
        if (returnResult3.getCid() != null) {
          // 最終戻り値設定
          returnResultList.add(returnResult3);
        }
      }
    }
    // 要対応有無の降順 かつ 対応期日の昇順で結合後の２次元配列（もしくはリスト）をソートする
    if (returnResultList.size() != 0) {
      returnResultListSort = returnResultList.stream().sorted(Comparator.comparing(ReturnResult::getCorrespondence)
          .reversed().thenComparing(ReturnResult::getCorrespondDate)).collect(Collectors.toList());
    }
    return returnResultListSort;
  }
}
