package com.web.app.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.ReturnResult;
import com.web.app.domain.SearchDetail;
import com.web.app.domain.SelectCondition;
import com.web.app.mapper.SearchDetailCaseMapper;
import com.web.app.service.SearchDetailCaseService;

@Service
public class SearchDetailCaseServiceImpl implements SearchDetailCaseService {

  @Autowired
  private SearchDetailCaseMapper searchDetailCaseMapper;

  @Override
  @Transactional
  public ReturnResult searchSetailCase(SelectCondition searchCase) {
    ReturnResult returnResult = new ReturnResult();
    SearchDetail searchDetail = searchDetailCaseMapper.searchDetail(searchCase);
    // 対応期日の設定
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    switch (searchDetail.getCaseStage()) {
      case 0:
      if(searchDetail.getReplyEndDate() != null){
        returnResult.setCorrespondDate(simpleDateFormat.format(searchDetail.getReplyEndDate()));
      }
        break;
      case 2:
      if(searchDetail.getConuterclaimEndDate() != null){
        returnResult.setCorrespondDate(simpleDateFormat.format(searchDetail.getConuterclaimEndDate()));
      }
        break;
      case 3:
      if(searchDetail.getNegotiationEndDate() != null){
        returnResult.setCorrespondDate(simpleDateFormat.format(searchDetail.getNegotiationEndDate()));
      }
        break;
      case 6:
      case 7:
      if(searchDetail.getMediationEndDate() != null){
        returnResult.setCorrespondDate(simpleDateFormat.format(searchDetail.getMediationEndDate()));
      }
        break;
      default:
        returnResult.setCorrespondDate("99999999");
    }

    // 要対応有無の設定
    if (searchCase.getPositionFlg() == 1) {
      switch (searchDetail.getCaseStatus()) {
        case "2":
          returnResult.setCorrespondence("1");
          break;
        case "3":
          if(searchDetail.getNegotiationEndDateChangeStatus() == 1
          || searchDetail.getStatus() == null
          || searchDetail.getStatus() == 1
          || searchDetail.getStatus() == 2
          || searchDetail.getStatus() == 3
          || searchDetail.getStatus() == 5
          || searchDetail.getStatus() == 7
          || searchDetail.getStatus() == 8
          || searchDetail.getStatus() == 12
          || searchDetail.getStatus() == 13
          || searchDetail.getStatus() == 14){
            returnResult.setCorrespondence("1");
          }
          break;
        case "6":
          returnResult.setCorrespondence("1");
          break;
        case "7":
          if(searchDetail.getMediationsStatus() == 1
          || searchDetail.getMediationsStatus() == 3
          || searchDetail.getMediationsStatus() == 8
          || searchDetail.getGroupMessageFlag2() == 1){
            returnResult.setCorrespondence("1");
          }
          break;
        default:
          returnResult.setCorrespondence("0");
          break;
      }
    } else if(searchCase.getPositionFlg() == 2){
      switch (searchDetail.getCaseStatus()) {
        case "0":
          returnResult.setCorrespondence("1");
          break;
        case "3":
          if(searchDetail.getNegotiationEndDateChangeStatus() == 2
          || searchDetail.getStatus() == null
          || searchDetail.getStatus() == 0
          || searchDetail.getStatus() == 1
          || searchDetail.getStatus() == 3
          || searchDetail.getStatus() == 4
          || searchDetail.getStatus() == 9
          || searchDetail.getStatus() == 10
          || searchDetail.getStatus() == 11
          || searchDetail.getStatus() == 14
          || searchDetail.getStatus() == 15){
            returnResult.setCorrespondence("1");
          }
          break;
        case "6":
          returnResult.setCorrespondence("1");
          break;
        case "7":
          if(searchDetail.getMediationsStatus() == 1
          || searchDetail.getMediationsStatus() == 2
          || searchDetail.getMediationsStatus() == 7
          || searchDetail.getGroupMessageFlag1() == 1){
            returnResult.setCorrespondence("1");
          }
          break;
        default:
          returnResult.setCorrespondence("0");
          break;
      }
    }else if(searchCase.getPositionFlg() == 3){
      switch (searchDetail.getCaseStatus()) {
        case "6":
          returnResult.setCorrespondence("1");
          break;
        case "7":
          if(searchDetail.getMediationsStatus() == 0
          || searchDetail.getMediationsStatus() == null){
            returnResult.setCorrespondence("1");
          }
          break;
        default:
          returnResult.setCorrespondence("0");
          break;
      }
    }

    int notReadedCnt = 0;
    // 未読メッセージ件数の取得
    if(searchCase.getPositionFlg() == 3){
      // ステージ：6　調停人指名中（未受理の場合
      if("6".equals(searchDetail.getCaseStatus())){
        if(searchDetailCaseMapper.getMediatorDisclosureFlag(searchCase.getCaseId()) == 1){
          notReadedCnt = searchDetailCaseMapper.getMsgCountByFlag1(searchCase.getCaseId(),searchCase.getPetitionUserId());
        } else {
          notReadedCnt = searchDetailCaseMapper.getMsgCountByFlagNo1(searchCase.getCaseId(),searchCase.getPetitionUserId());
        }
      }
    } else {
      notReadedCnt = searchDetailCaseMapper.getMsgCountByFlag1(searchCase.getCaseId(),searchCase.getPetitionUserId());
    }

    if(notReadedCnt > 0){
      returnResult.setMsgCount(notReadedCnt);
      returnResult.setCorrespondence("1");
    } else {
      returnResult.setMsgCount(0);
    }

    returnResult.setPetitionDate(searchDetail.getPetitonDate());
    returnResult.setCid(searchDetail.getCid());
    returnResult.setCaseTitle(searchDetail.getCaseTitle());
    returnResult.setCaseStatus(searchDetail.getCaseStatus());
    returnResult.setPositionFlg(searchCase.getPositionFlg());

    return returnResult;
  }
}