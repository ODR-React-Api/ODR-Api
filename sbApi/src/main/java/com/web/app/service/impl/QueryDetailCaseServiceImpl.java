package com.web.app.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.MosList.ReturnResult;
import com.web.app.domain.MosList.SearchDetail;
import com.web.app.mapper.QueryDetailCaseMapper;
import com.web.app.service.QueryDetailCaseService;

@Service
public class QueryDetailCaseServiceImpl implements QueryDetailCaseService {

    @Autowired
    private QueryDetailCaseMapper queryDetailCaseMapper;

    @Override
    @Transactional
    public ReturnResult getQueryDetailCase(String caseId, String petitionUserId, Integer positionFlag,
            String queryString) {
        ReturnResult returnResult = new ReturnResult();
        // 申立て番号、件名、登録日付、対応期日、状態、要対応有無の取得
        SearchDetail queryDetailCase = queryDetailCaseMapper.getQueryDetailCase(caseId, queryString);
        if (queryDetailCase != null) {
            returnResult.setPetitionDate(queryDetailCase.getPetitonDate());
            returnResult.setCid(queryDetailCase.getCid());
            returnResult.setCaseTitle(queryDetailCase.getCaseTitle());
            returnResult.setCaseStatus(queryDetailCase.getCaseStatus());

            System.out.println("queryDetailCase:" + queryDetailCase);

            // 対応期日の設定
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            returnResult.setCorrespondDate("99999999");
            switch (queryDetailCase.getCaseStage()) {
                case 0:
                    if (queryDetailCase.getReplyEndDate() != null) {
                        returnResult.setCorrespondDate(simpleDateFormat.format(queryDetailCase.getReplyEndDate()));
                    }
                    break;
                case 2:
                    if (queryDetailCase.getConuterclaimEndDate() != null) {
                        returnResult
                                .setCorrespondDate(simpleDateFormat.format(queryDetailCase.getConuterclaimEndDate()));
                    }
                    break;
                case 3:
                    if (queryDetailCase.getNegotiationEndDate() != null) {
                        returnResult
                                .setCorrespondDate(simpleDateFormat.format(queryDetailCase.getNegotiationEndDate()));
                    }
                    break;
                case 6:
                case 7:
                    if (queryDetailCase.getMediationEndDate() != null) {
                        returnResult.setCorrespondDate(simpleDateFormat.format(queryDetailCase.getMediationEndDate()));
                    }
                    break;
                default:
                    returnResult.setCorrespondDate("99999999");
            }

            // 要対応有無の設定
            returnResult.setCorrespondence("0");
            if (positionFlag == 1) {
                switch (queryDetailCase.getCaseStage()) {
                    case 2:
                        returnResult.setCorrespondence("1");
                        break;
                    case 3:
                        if (queryDetailCase.getNegotiationEndDateChangeStatus() == 1
                                || queryDetailCase.getStatus() == null
                                || queryDetailCase.getStatus() == 1
                                || queryDetailCase.getStatus() == 2
                                || queryDetailCase.getStatus() == 3
                                || queryDetailCase.getStatus() == 5
                                || queryDetailCase.getStatus() == 7
                                || queryDetailCase.getStatus() == 8
                                || queryDetailCase.getStatus() == 12
                                || queryDetailCase.getStatus() == 13
                                || queryDetailCase.getStatus() == 14) {
                            returnResult.setCorrespondence("1");
                        }
                        break;
                    case 6:
                        returnResult.setCorrespondence("1");
                        break;
                    case 7:
                        if (queryDetailCase.getMediationsStatus() == 1
                                || queryDetailCase.getMediationsStatus() == 3
                                || queryDetailCase.getMediationsStatus() == 8
                                || queryDetailCase.getGroupMessageFlag2() == 1) {
                            returnResult.setCorrespondence("1");
                        }
                        break;
                    default:
                        returnResult.setCorrespondence("0");
                        break;
                }
            } else if (positionFlag == 2) {
                switch (queryDetailCase.getCaseStage()) {
                    case 0:
                        returnResult.setCorrespondence("1");
                        break;
                    case 3:
                        if (queryDetailCase.getNegotiationEndDateChangeStatus() == 2
                                || queryDetailCase.getStatus() == null
                                || queryDetailCase.getStatus() == 0
                                || queryDetailCase.getStatus() == 1
                                || queryDetailCase.getStatus() == 3
                                || queryDetailCase.getStatus() == 4
                                || queryDetailCase.getStatus() == 9
                                || queryDetailCase.getStatus() == 10
                                || queryDetailCase.getStatus() == 11
                                || queryDetailCase.getStatus() == 14
                                || queryDetailCase.getStatus() == 15) {
                            returnResult.setCorrespondence("1");
                        }
                        break;
                    case 6:
                        returnResult.setCorrespondence("1");
                        break;
                    case 7:
                        if (queryDetailCase.getMediationsStatus() == 1
                                || queryDetailCase.getMediationsStatus() == 2
                                || queryDetailCase.getMediationsStatus() == 7
                                || queryDetailCase.getGroupMessageFlag1() == 1) {
                            returnResult.setCorrespondence("1");
                        }
                        break;
                    default:
                        returnResult.setCorrespondence("0");
                        break;
                }
            } else if (positionFlag == 2) {
                switch (queryDetailCase.getCaseStage()) {
                    case 6:
                        returnResult.setCorrespondence("1");
                        break;
                    case 7:
                        if (queryDetailCase.getMediationsStatus() == null
                                || queryDetailCase.getMediationsStatus() == 0) {
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
            if (positionFlag == 3) {
                // ステージ：6 調停人指名中（未受理の場合
                if ("6".equals(queryDetailCase.getCaseStatus())) {
                    if (queryDetailCaseMapper.getMediatorDisclosureFlag(caseId) == 1) {
                        notReadedCnt = queryDetailCaseMapper.getMsgCountByFlag1(caseId, petitionUserId);
                    } else {
                        notReadedCnt = queryDetailCaseMapper.getMsgCountByFlagNo1(caseId, petitionUserId);
                    }
                }
            } else {
                notReadedCnt = queryDetailCaseMapper.getMsgCountByFlag1(caseId, petitionUserId);
            }

            if (notReadedCnt > 0) {
                returnResult.setMsgCount(notReadedCnt);
                returnResult.setCorrespondence("1");
            } else {
                returnResult.setMsgCount(0);
            }
        }

        return returnResult;
    }

}
