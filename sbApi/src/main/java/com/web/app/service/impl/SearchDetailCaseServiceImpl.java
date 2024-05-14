package com.web.app.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.ReturnResult;
import com.web.app.domain.SearchDetail;
import com.web.app.domain.SelectCondition;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.SearchDetailCaseMapper;
import com.web.app.service.SearchDetailCaseService;

/**
 * API「 検索用一覧取得」より渡された引数によって、DBからケース詳細を取得するService
 * 
 * @author DUC 張万超
 * @since 2024/4/22
 * @version 1.0
 */

@Service
public class SearchDetailCaseServiceImpl implements SearchDetailCaseService {

    @Autowired
    private SearchDetailCaseMapper searchDetailCaseMapper;

    /**
     * API「 検索用一覧取得」より渡された引数によって、DBからケース詳細を取得する
     *
     * @param searchCase API「 検索用一覧取得」より渡された引数
     * @return case詳細
     */

    @Override
    @Transactional
    public ReturnResult searchSetailCase(SelectCondition searchCase) {
        // 戻り値オブジェクトの作成
        ReturnResult returnResult = new ReturnResult();
        // 申立て番号、件名、登録日付、対応期日、状態、要対応有無の取得
        SearchDetail searchDetail = searchDetailCaseMapper.searchDetail(searchCase);

        // 対応期日の設定
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        switch (searchDetail.getCaseStage()) {
            case Constants.STR_CASES_CASESTAGE_0:
                if (searchDetail.getReplyEndDate() != null) {
                    returnResult.setCorrespondDate(simpleDateFormat.format(searchDetail.getReplyEndDate()));
                }
                break;
            case Constants.STR_CASES_CASESTAGE_2:
                if (searchDetail.getConuterclaimEndDate() != null) {
                    returnResult.setCorrespondDate(simpleDateFormat.format(searchDetail.getConuterclaimEndDate()));
                }
                break;
            case Constants.STR_CASES_CASESTAGE_3:
                if (searchDetail.getNegotiationEndDate() != null) {
                    returnResult.setCorrespondDate(simpleDateFormat.format(searchDetail.getNegotiationEndDate()));
                }
                break;
            case Constants.STR_CASES_CASESTAGE_6:
            case Constants.STR_CASES_CASESTAGE_7:
                if (searchDetail.getMediationEndDate() != null) {
                    returnResult.setCorrespondDate(simpleDateFormat.format(searchDetail.getMediationEndDate()));
                }
                break;
            default:
                returnResult.setCorrespondDate(Constants.DEFAULT_CORRESPONDDATE);
        }

        // 要対応有無の設定
        if (searchCase.getPositionFlg() == Constants.POSITIONFLAG_PETITION) {
            switch (searchDetail.getCaseStatus()) {
                case Constants.STR_CASES_CASESTATUS_2:
                    returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                    break;
                case Constants.STR_CASES_CASESTATUS_3:
                    if (searchDetail.getNegotiationEndDateChangeStatus() == Constants.NUM_1
                            || searchDetail.getStatus() == null
                            || searchDetail.getStatus() == Constants.NUM_1
                            || searchDetail.getStatus() == Constants.NUM_2
                            || searchDetail.getStatus() == Constants.NUM_3
                            || searchDetail.getStatus() == Constants.NUM_5
                            || searchDetail.getStatus() == Constants.NUM_7
                            || searchDetail.getStatus() == Constants.NUM_8
                            || searchDetail.getStatus() == Constants.NUM_12
                            || searchDetail.getStatus() == Constants.NUM_13
                            || searchDetail.getStatus() == Constants.NUM_14) {
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                    }
                    break;
                case Constants.STR_CASES_CASESTATUS_6:
                    returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                    break;
                case Constants.STR_CASES_CASESTATUS_7:
                    if (searchDetail.getMediationsStatus() == Constants.NUM_1
                            || searchDetail.getMediationsStatus() == Constants.NUM_3
                            || searchDetail.getMediationsStatus() == Constants.NUM_8
                            || searchDetail.getGroupMessageFlag2() == Constants.NUM_1) {
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                    }
                    break;
                default:
                    returnResult.setCorrespondence(Constants.CORRESPONDENCE_0);
                    break;
            }
        } else if (searchCase.getPositionFlg() == Constants.POSITIONFLAG_TRADER) {
            switch (searchDetail.getCaseStatus()) {
                case Constants.STR_CASES_CASESTATUS_0:
                    returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                    break;
                case Constants.STR_CASES_CASESTATUS_3:
                    if (searchDetail.getNegotiationEndDateChangeStatus() == Constants.NUM_2
                            || searchDetail.getStatus() == null
                            || searchDetail.getStatus() == Constants.NUM_0
                            || searchDetail.getStatus() == Constants.NUM_1
                            || searchDetail.getStatus() == Constants.NUM_3
                            || searchDetail.getStatus() == Constants.NUM_4
                            || searchDetail.getStatus() == Constants.NUM_9
                            || searchDetail.getStatus() == Constants.NUM_10
                            || searchDetail.getStatus() == Constants.NUM_11
                            || searchDetail.getStatus() == Constants.NUM_14
                            || searchDetail.getStatus() == Constants.NUM_15) {
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                    }
                    break;
                case Constants.STR_CASES_CASESTATUS_6:
                    returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                    break;
                case Constants.STR_CASES_CASESTATUS_7:
                    if (searchDetail.getMediationsStatus() == Constants.NUM_1
                            || searchDetail.getMediationsStatus() == Constants.NUM_2
                            || searchDetail.getMediationsStatus() == Constants.NUM_7
                            || searchDetail.getGroupMessageFlag1() == Constants.NUM_1) {
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                    }
                    break;
                default:
                    returnResult.setCorrespondence(Constants.CORRESPONDENCE_0);
                    break;
            }
        } else if (searchCase.getPositionFlg() == Constants.POSITIONFLAG_MEDIATOR) {
            switch (searchDetail.getCaseStatus()) {
                case Constants.STR_CASES_CASESTATUS_6:
                    returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                    break;
                case Constants.STR_CASES_CASESTATUS_7:
                    if (searchDetail.getMediationsStatus() == Constants.NUM_0
                            || searchDetail.getMediationsStatus() == null) {
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                    }
                    break;
                default:
                    returnResult.setCorrespondence(Constants.CORRESPONDENCE_0);
                    break;
            }
        }

        // 未読メッセージ件数はデフォルトで0
        int notReadedCnt = Constants.NUM_0;
        // 未読メッセージ件数の取得
        if (searchCase.getPositionFlg() == Constants.POSITIONFLAG_MEDIATOR) {
            // ステージ：6 調停人指名中（未受理の場合
            if ("6".equals(searchDetail.getCaseStatus())) {
                if (searchDetailCaseMapper.getMediatorDisclosureFlag(searchCase.getCaseId()) == Constants.NUM_1) {
                    notReadedCnt = searchDetailCaseMapper.getMsgCountByFlag(searchCase.getCaseId(),
                            searchCase.getPetitionUserId());
                } else {
                    notReadedCnt = searchDetailCaseMapper.getMsgCountByFlagNo(searchCase.getCaseId(),
                            searchCase.getPetitionUserId());
                }
            }
        } else {
            notReadedCnt = searchDetailCaseMapper.getMsgCountByFlag(searchCase.getCaseId(),
                    searchCase.getPetitionUserId());
        }

        if (notReadedCnt > Constants.NUM_0) {
            returnResult.setMsgCount(notReadedCnt);
            returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
        } else {
            returnResult.setMsgCount(Constants.NUM_0);
        }

        // 戻りオブジェクトへのデータの移入
        returnResult.setPetitionDate(searchDetail.getPetitonDate());
        returnResult.setCid(searchDetail.getCid());
        returnResult.setCaseTitle(searchDetail.getCaseTitle());
        returnResult.setCaseStatus(searchDetail.getCaseStatus());
        returnResult.setPositionFlg(searchCase.getPositionFlg());

        return returnResult;
    }
}