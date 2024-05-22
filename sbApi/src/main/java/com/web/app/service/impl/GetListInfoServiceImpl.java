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
import com.web.app.mapper.GetListInfoMapper;
import com.web.app.service.GetListInfoService;

@Service
public class GetListInfoServiceImpl implements GetListInfoService {

  // 引入Dao层
  @Autowired
  private GetListInfoMapper getListInfoMapper;

  @Override
  public List<ReturnResult> getListInfo(String uid) {

    // // ユーザ情報のメールを初期化
    // String email = null;
    // 立場フラグ：1（申立人）を初期化
    Integer flag1 = 1;
    // 立場フラグ：2（相手方）を初期化
    Integer flag2 = 2;
    // 立場フラグ：3（調停人）を初期化
    Integer flag3 = 3;
    // 返回值初始化
    List<ReturnResult> returnResultList = new ArrayList<>();
    // 最终返回值初始化
    List<ReturnResult> returnResultList2 = new ArrayList<>();

    // ユーザ情報を取得する
    String email = getListInfoMapper.selectEmailOdrUsers(uid);

    // ユーザが申立人の場合
    List<UserCase> caseIdPetition1 = getListInfoMapper.selectCaseIdPetition1(email);

    // 调用API「ケース詳細取得」参数初始化
    CaseDetails caseDetails1 = new CaseDetails();
    // 立場フラグ
    caseDetails1.setFlag(flag1);
    // セッション.ユーザID
    caseDetails1.setUid(uid);
    // ユーザ情報
    if (caseIdPetition1.size() > 0) {
      for (int i = 0; i < caseIdPetition1.size(); i++) {
        // ①取得したCaseId
        caseDetails1.setCaseId(caseIdPetition1.get(i).getCaseId());
        // ①取得したPetitionUserId
        caseDetails1.setPetitionUserId(caseIdPetition1.get(i).getPetitionUserId());
        // API「ケース詳細取得」を呼び出す。
        // TODO
        ReturnResult returnResult1 = new ReturnResult();
        // 相手方详细情报
        if (returnResult1.getCid() != null) {
          // 最终返回值设定
          returnResultList.add(returnResult1);
        }
      }
    }

    // ユーザが相手方の場合
    List<UserCase> caseIdPetition2 = getListInfoMapper.selectCaseIdPetition2(email);

    // 调用API「ケース詳細取得」参数初始化
    CaseDetails caseDetails2 = new CaseDetails();
    // 立場フラグ
    caseDetails2.setFlag(flag2);
    // セッション.ユーザID
    caseDetails2.setUid(uid);
    // ユーザ情報
    if (caseIdPetition2.size() > 0) {
      for (int i = 0; i < caseIdPetition2.size(); i++) {
        // ①取得したCaseId
        caseDetails2.setCaseId(caseIdPetition2.get(i).getCaseId());
        // ①取得したPetitionUserId
        caseDetails2.setPetitionUserId(caseIdPetition2.get(i).getPetitionUserId());
        // API「ケース詳細取得」を呼び出す。
        // TODO
        ReturnResult returnResult2 = new ReturnResult();
        // 相手方详细情报
        returnResultList.add(returnResult2);
      }
    }

    // ユーザが調停人の場合
    List<UserCase> caseIdPetition3 = getListInfoMapper.selectCaseIdPetition3(email);

    // 调用API「ケース詳細取得」参数初始化
    CaseDetails caseDetails3 =  new CaseDetails();
    // 立場フラグ
    caseDetails3.setFlag(flag3);
    // セッション.ユーザID
    caseDetails3.setUid(uid);
    // ユーザ情報
    if (caseIdPetition3.size() > 0) {
      for (int i = 0; i < caseIdPetition3.size(); i++) {
        // ①取得したCaseId
        caseDetails3.setCaseId(caseIdPetition3.get(i).getCaseId());
        // ①取得したPetitionUserId
        caseDetails3.setPetitionUserId(caseIdPetition3.get(i).getPetitionUserId());
        // API「ケース詳細取得」を呼び出す。
        // TODO
        ReturnResult returnResult3 = new ReturnResult();
        // 相手方详细情报
        returnResultList.add(returnResult3);
      }
    }
    // 要対応有無の降順　かつ　対応期日の昇順で結合後の２次元配列（もしくはリスト）をソートする
    // if (returnResultList.size() != 0) {
      returnResultList2 = returnResultList.stream().sorted(Comparator.comparing(ReturnResult::getCorrespondence)
      .reversed().thenComparing(ReturnResult::getCorrespondDate)).collect(Collectors.toList());
    // }
    return returnResultList2;
  }
}
