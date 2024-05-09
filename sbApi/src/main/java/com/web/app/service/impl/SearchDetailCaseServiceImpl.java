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
            case 0:
                if (searchDetail.getReplyEndDate() != null) {
                    returnResult.setCorrespondDate(simpleDateFormat.format(searchDetail.getReplyEndDate()));
                }
                break;
            case 2:
                if (searchDetail.getConuterclaimEndDate() != null) {
                    returnResult.setCorrespondDate(simpleDateFormat.format(searchDetail.getConuterclaimEndDate()));
                }
                break;
            case 3:
                if (searchDetail.getNegotiationEndDate() != null) {
                    returnResult.setCorrespondDate(simpleDateFormat.format(searchDetail.getNegotiationEndDate()));
                }
                break;
            case 6:
            case 7:
                if (searchDetail.getMediationEndDate() != null) {
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
                    if (searchDetail.getNegotiationEndDateChangeStatus() == 1
                            || searchDetail.getStatus() == null
                            || searchDetail.getStatus() == 1
                            || searchDetail.getStatus() == 2
                            || searchDetail.getStatus() == 3
                            || searchDetail.getStatus() == 5
                            || searchDetail.getStatus() == 7
                            || searchDetail.getStatus() == 8
                            || searchDetail.getStatus() == 12
                            || searchDetail.getStatus() == 13
                            || searchDetail.getStatus() == 14) {
                        returnResult.setCorrespondence("1");
                    }
                    break;
                case "6":
                    returnResult.setCorrespondence("1");
                    break;
                case "7":
                    if (searchDetail.getMediationsStatus() == 1
                            || searchDetail.getMediationsStatus() == 3
                            || searchDetail.getMediationsStatus() == 8
                            || searchDetail.getGroupMessageFlag2() == 1) {
                        returnResult.setCorrespondence("1");
                    }
                    break;
                default:
                    returnResult.setCorrespondence("0");
                    break;
            }
        } else if (searchCase.getPositionFlg() == 2) {
            switch (searchDetail.getCaseStatus()) {
                case "0":
                    returnResult.setCorrespondence("1");
                    break;
                case "3":
                    if (searchDetail.getNegotiationEndDateChangeStatus() == 2
                            || searchDetail.getStatus() == null
                            || searchDetail.getStatus() == 0
                            || searchDetail.getStatus() == 1
                            || searchDetail.getStatus() == 3
                            || searchDetail.getStatus() == 4
                            || searchDetail.getStatus() == 9
                            || searchDetail.getStatus() == 10
                            || searchDetail.getStatus() == 11
                            || searchDetail.getStatus() == 14
                            || searchDetail.getStatus() == 15) {
                        returnResult.setCorrespondence("1");
                    }
                    break;
                case "6":
                    returnResult.setCorrespondence("1");
                    break;
                case "7":
                    if (searchDetail.getMediationsStatus() == 1
                            || searchDetail.getMediationsStatus() == 2
                            || searchDetail.getMediationsStatus() == 7
                            || searchDetail.getGroupMessageFlag1() == 1) {
                        returnResult.setCorrespondence("1");
                    }
                    break;
                default:
                    returnResult.setCorrespondence("0");
                    break;
            }
        } else if (searchCase.getPositionFlg() == 3) {
            switch (searchDetail.getCaseStatus()) {
                case "6":
                    returnResult.setCorrespondence("1");
                    break;
                case "7":
                    if (searchDetail.getMediationsStatus() == 0
                            || searchDetail.getMediationsStatus() == null) {
                        returnResult.setCorrespondence("1");
                    }
                    break;
                default:
                    returnResult.setCorrespondence("0");
                    break;
            }
        }

        // 未読メッセージ件数はデフォルトで0
        int notReadedCnt = 0;
        // 未読メッセージ件数の取得
        if (searchCase.getPositionFlg() == 3) {
            // ステージ：6 調停人指名中（未受理の場合
            if ("6".equals(searchDetail.getCaseStatus())) {
                if (searchDetailCaseMapper.getMediatorDisclosureFlag(searchCase.getCaseId()) == 1) {
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

        if (notReadedCnt > 0) {
            returnResult.setMsgCount(notReadedCnt);
            returnResult.setCorrespondence("1");
        } else {
            returnResult.setMsgCount(0);
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