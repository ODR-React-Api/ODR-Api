package com.web.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.MosList.DraftSavingDate;
import com.web.app.domain.MosList.ReturnResult;
import com.web.app.domain.MosList.SearchDetail;
import com.web.app.domain.MosList.SelectCondition;
import com.web.app.domain.MosList.SelectUserInfoForCase;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.FuzzyQueryDetailCaseMapper;
import com.web.app.mapper.GetFuzzyQueryListInfoMapper;
import com.web.app.mapper.GetSaveDataInfoMapper;
import com.web.app.mapper.SearchDetailCaseMapper;
import com.web.app.service.MosListService;

/**
 * 申立て一覧画面
 * 
 * @author DUC 張万超
 * @since 2024/4/22
 * @version 1.0
 */
@Service
public class MosListServiceImpl implements MosListService {

    // API_検索用ケース詳細取得
    @Autowired
    private SearchDetailCaseMapper searchDetailCaseMapper;

    // API_曖昧検索用一覧取得
    @Autowired
    private GetFuzzyQueryListInfoMapper getFuzzyQueryListInfoMapper;

    // API_曖昧検索用ケース詳細取得
    @Autowired
    private FuzzyQueryDetailCaseMapper fuzzyQueryDetailCaseMapper;

    // API_申立て登録下書き保存データ取得
    @Autowired
    private GetSaveDataInfoMapper getSaveDataInfoMapper;

    /**
     * API「検索用一覧取得」より渡された引数によって、DBからケース詳細を取得する
     *
     * @param searchCase API「 検索用一覧取得」より渡された引数
     * @return case詳細
     */
    @Override
    @Transactional
    public ReturnResult searchDetailCase(SelectCondition searchCase) {
        // 戻り値オブジェクトの作成
        ReturnResult returnResult = new ReturnResult();
        // 申立て番号、件名、登録日付、対応期日、状態、要対応有無の取得
        SearchDetail searchDetail = searchDetailCaseMapper.searchDetail(searchCase);

        if (searchDetail != null) {
            // 対応期日の設定
            // Format設定
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            returnResult.setCorrespondDate(Constants.DEFAULT_CORRESPONDDATE);
            // 取得したCaseStageの値を判定して
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
            returnResult.setCorrespondence(Constants.CORRESPONDENCE_0);
            if (searchCase.getPositionFlg() == Constants.POSITIONFLAG_PETITION) {
                switch (searchDetail.getCaseStatus()) {
                    case Constants.STR_CASES_CASESTATUS_2:
                        // 要対応有り
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
                            // 要対応有り
                            returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        }
                        break;
                    case Constants.STR_CASES_CASESTATUS_6:
                        // 要対応有り
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        break;
                    case Constants.STR_CASES_CASESTATUS_7:
                        if (searchDetail.getMediationsStatus() == Constants.NUM_1
                                || searchDetail.getMediationsStatus() == Constants.NUM_3
                                || searchDetail.getMediationsStatus() == Constants.NUM_8
                                || searchDetail.getGroupMessageFlag2() == Constants.NUM_1) {
                            // 要対応有り
                            returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        }
                        break;
                    default:
                        // 要対応なし
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_0);
                        break;
                }
            } else if (searchCase.getPositionFlg() == Constants.POSITIONFLAG_TRADER) {
                switch (searchDetail.getCaseStatus()) {
                    case Constants.STR_CASES_CASESTATUS_0:
                        // 要対応有り
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
                            // 要対応有り
                            returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        }
                        break;
                    case Constants.STR_CASES_CASESTATUS_6:
                        // 要対応有り
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        break;
                    case Constants.STR_CASES_CASESTATUS_7:
                        if (searchDetail.getMediationsStatus() == Constants.NUM_1
                                || searchDetail.getMediationsStatus() == Constants.NUM_2
                                || searchDetail.getMediationsStatus() == Constants.NUM_7
                                || searchDetail.getGroupMessageFlag1() == Constants.NUM_1) {
                            // 要対応有り
                            returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        }
                        break;
                    default:
                        // 要対応なし
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_0);
                        break;
                }
            } else if (searchCase.getPositionFlg() == Constants.POSITIONFLAG_MEDIATOR) {
                switch (searchDetail.getCaseStatus()) {
                    case Constants.STR_CASES_CASESTATUS_6:
                        // 要対応有り
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        break;
                    case Constants.STR_CASES_CASESTATUS_7:
                        if (searchDetail.getMediationsStatus() == Constants.NUM_0
                                || searchDetail.getMediationsStatus() == null) {
                            // 要対応有り
                            returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        }
                        break;
                    default:
                        // 要対応なし
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_0);
                        break;
                }
            }

            // 未読メッセージ件数はデフォルトで0
            int notReadedCnt = Constants.NUM_0;
            // 未読メッセージ件数の取得
            if (searchCase.getPositionFlg() == Constants.POSITIONFLAG_MEDIATOR) {
                // ステージ：6 調停人指名中（未受理の場合
                if (Constants.STR_CASES_CASESTATUS_6.equals(searchDetail.getCaseStatus())) {
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

        return null;
    }

    /**
     * 検索Boxに入力した文字列で申立て番号と件名の一部検索条件として、ユーザに関連するすべてのケースをDBから検索する。
     *
     * @param uid         画面.ユーザID
     * @param queryString 画面.検索Box入力文字列
     * @return 取得されたケース情報リスト
     */
    @Override
    @Transactional
    public List<ReturnResult> getFuzzyQueryListInfo(String uid, String queryString) {

        // 取得したデータを保存する
        List<ReturnResult> results = new ArrayList<>();

        // ユーザ情報を取得
        String email = getFuzzyQueryListInfoMapper.getUserInfo(uid);

        // ケースの取得
        List<SelectUserInfoForCase> selectUserInfoForCasesFromPetiton = getFuzzyQueryListInfoMapper
                .selectUserInfoForCasesFromPetiton(email);

        // ユーザが申立人とするケースの取得
        for (SelectUserInfoForCase selectUserInfoForCase : selectUserInfoForCasesFromPetiton) {
            ReturnResult queryCase = getFuzzyQueryDetailCase(selectUserInfoForCase.getCaseId(),
                    selectUserInfoForCase.getPetitionUserId(), Constants.POSITIONFLAG_PETITION, queryString);

            // 有効なデータを取得するとリストに追加
            if (queryCase != null) {
                queryCase.setPositionFlg(Constants.POSITIONFLAG_PETITION);
                results.add(queryCase);
            }
        }

        List<SelectUserInfoForCase> selectUserInfoForCasesFromTrader = getFuzzyQueryListInfoMapper
                .selectUserInfoForCasesFromTrader(email);

        // ユーザが相手方とするケースの取得
        for (SelectUserInfoForCase selectUserInfoForCase : selectUserInfoForCasesFromTrader) {
            // ReturnResult queryCase = new ReturnResult();
            ReturnResult queryCase = getFuzzyQueryDetailCase(selectUserInfoForCase.getCaseId(),
                    selectUserInfoForCase.getPetitionUserId(), Constants.POSITIONFLAG_TRADER, queryString);

            // 有効なデータを取得するとリストに追加
            if (queryCase != null) {
                queryCase.setPositionFlg(Constants.POSITIONFLAG_TRADER);
                results.add(queryCase);
            }
        }

        List<SelectUserInfoForCase> selectUserInfoForCasesFromMediator = getFuzzyQueryListInfoMapper
                .selectUserInfoForCasesFromMediator(email);

        // ユーザが調停人とするケースの取得
        for (SelectUserInfoForCase selectUserInfoForCase : selectUserInfoForCasesFromMediator) {
            ReturnResult queryCase = getFuzzyQueryDetailCase(selectUserInfoForCase.getCaseId(),
                    selectUserInfoForCase.getPetitionUserId(), Constants.POSITIONFLAG_MEDIATOR, queryString);

            // 有効なデータを取得するとリストに追加
            if (queryCase != null) {
                queryCase.setPositionFlg(Constants.POSITIONFLAG_MEDIATOR);
                results.add(queryCase);
            }
        }
        return results;
    }

    /**
     * API「 曖昧検索用一覧取得」より渡された引数で、DBからケース詳細を取得する。
     *
     * @param caseId CaseId
     * @param petitionUserId case申立て人
     * @param positionFlag 立場フラグ
     * @param queryString 画面.検索Box入力文字列
     * @return 取得されたケース情報リスト
     */
    @Override
    @Transactional
    public ReturnResult getFuzzyQueryDetailCase(String caseId, String petitionUserId, Integer positionFlag,
            String queryString) {
        // データを返すオブジェクトを作成する
        ReturnResult returnResult = new ReturnResult();
        // 申立て番号、件名、登録日付、対応期日、状態、要対応有無の取得
        SearchDetail queryDetailCase = fuzzyQueryDetailCaseMapper.getQueryDetailCase(caseId, queryString);
        if (queryDetailCase != null) {
            // データアセンブリ
            returnResult.setPetitionDate(queryDetailCase.getPetitonDate());
            returnResult.setCid(queryDetailCase.getCid());
            returnResult.setCaseTitle(queryDetailCase.getCaseTitle());
            returnResult.setCaseStatus(queryDetailCase.getCaseStatus());

            // 対応期日の設定
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            returnResult.setCorrespondDate(Constants.DEFAULT_CORRESPONDDATE);
            switch (queryDetailCase.getCaseStage()) {
                case Constants.STR_CASES_CASESTAGE_0:
                    if (queryDetailCase.getReplyEndDate() != null) {
                        returnResult.setCorrespondDate(simpleDateFormat.format(queryDetailCase.getReplyEndDate()));
                    }
                    break;
                case Constants.STR_CASES_CASESTAGE_2:
                    if (queryDetailCase.getConuterclaimEndDate() != null) {
                        returnResult
                                .setCorrespondDate(simpleDateFormat.format(queryDetailCase.getConuterclaimEndDate()));
                    }
                    break;
                case Constants.STR_CASES_CASESTAGE_3:
                    if (queryDetailCase.getNegotiationEndDate() != null) {
                        returnResult
                                .setCorrespondDate(simpleDateFormat.format(queryDetailCase.getNegotiationEndDate()));
                    }
                    break;
                case Constants.STR_CASES_CASESTAGE_6:
                case Constants.STR_CASES_CASESTAGE_7:
                    if (queryDetailCase.getMediationEndDate() != null) {
                        returnResult.setCorrespondDate(simpleDateFormat.format(queryDetailCase.getMediationEndDate()));
                    }
                    break;
                default:
                    returnResult.setCorrespondDate(Constants.DEFAULT_CORRESPONDDATE);
            }

            // 要対応有無の設定
            returnResult.setCorrespondence(Constants.CORRESPONDENCE_0);
            if (positionFlag == Constants.POSITIONFLAG_PETITION) {
                switch (queryDetailCase.getCaseStage()) {
                    case Constants.STR_CASES_CASESTAGE_2:
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        break;
                    case Constants.STR_CASES_CASESTAGE_3:
                        if (queryDetailCase.getNegotiationEndDateChangeStatus() == Constants.NUM_1
                                || queryDetailCase.getStatus() == null
                                || queryDetailCase.getStatus() == Constants.NUM_1
                                || queryDetailCase.getStatus() == Constants.NUM_2
                                || queryDetailCase.getStatus() == Constants.NUM_3
                                || queryDetailCase.getStatus() == Constants.NUM_5
                                || queryDetailCase.getStatus() == Constants.NUM_7
                                || queryDetailCase.getStatus() == Constants.NUM_8
                                || queryDetailCase.getStatus() == Constants.NUM_12
                                || queryDetailCase.getStatus() == Constants.NUM_13
                                || queryDetailCase.getStatus() == Constants.NUM_14) {
                            returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        }
                        break;
                    case Constants.STR_CASES_CASESTAGE_6:
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        break;
                    case Constants.STR_CASES_CASESTAGE_7:
                        if (queryDetailCase.getMediationsStatus() == Constants.NUM_1
                                || queryDetailCase.getMediationsStatus() == Constants.NUM_3
                                || queryDetailCase.getMediationsStatus() == Constants.NUM_8
                                || queryDetailCase.getGroupMessageFlag2() == Constants.NUM_1) {
                            returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        }
                        break;
                    default:
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_0);
                        break;
                }
            } else if (positionFlag == Constants.POSITIONFLAG_TRADER) {
                switch (queryDetailCase.getCaseStage()) {
                    case Constants.STR_CASES_CASESTAGE_0:
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        break;
                    case Constants.STR_CASES_CASESTAGE_3:
                        if (queryDetailCase.getNegotiationEndDateChangeStatus() == Constants.NUM_2
                                || queryDetailCase.getStatus() == null
                                || queryDetailCase.getStatus() == Constants.NUM_0
                                || queryDetailCase.getStatus() == Constants.NUM_1
                                || queryDetailCase.getStatus() == Constants.NUM_3
                                || queryDetailCase.getStatus() == Constants.NUM_4
                                || queryDetailCase.getStatus() == Constants.NUM_9
                                || queryDetailCase.getStatus() == Constants.NUM_10
                                || queryDetailCase.getStatus() == Constants.NUM_11
                                || queryDetailCase.getStatus() == Constants.NUM_14
                                || queryDetailCase.getStatus() == Constants.NUM_15) {
                            returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        }
                        break;
                    case Constants.STR_CASES_CASESTAGE_6:
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        break;
                    case Constants.STR_CASES_CASESTAGE_7:
                        if (queryDetailCase.getMediationsStatus() == Constants.NUM_1
                                || queryDetailCase.getMediationsStatus() == Constants.NUM_2
                                || queryDetailCase.getMediationsStatus() == Constants.NUM_7
                                || queryDetailCase.getGroupMessageFlag1() == Constants.NUM_1) {
                            returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        }
                        break;
                    default:
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_0);
                        break;
                }
            } else if (positionFlag == Constants.POSITIONFLAG_MEDIATOR) {
                switch (queryDetailCase.getCaseStage()) {
                    case Constants.STR_CASES_CASESTAGE_6:
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        break;
                    case Constants.STR_CASES_CASESTAGE_7:
                        if (queryDetailCase.getMediationsStatus() == null
                                || queryDetailCase.getMediationsStatus() == Constants.NUM_0) {
                            returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        }
                        break;
                    default:
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_0);
                        break;
                }
            }

            int notReadedCnt = Constants.NUM_0;
            // 未読メッセージ件数の取得
            if (positionFlag == Constants.POSITIONFLAG_MEDIATOR) {
                // ステージ：6 調停人指名中(未受理の場合)
                if (Constants.STR_CASES_CASESTATUS_6.equals(queryDetailCase.getCaseStatus())) {
                    if (fuzzyQueryDetailCaseMapper.getMediatorDisclosureFlag(caseId) == Constants.NUM_1) {
                        notReadedCnt = fuzzyQueryDetailCaseMapper.getMsgCountByFlag(caseId, petitionUserId);
                    } else {
                        notReadedCnt = fuzzyQueryDetailCaseMapper.getMsgCountByFlagNo(caseId, petitionUserId);
                    }
                }
            } else {
                notReadedCnt = fuzzyQueryDetailCaseMapper.getMsgCountByFlag(caseId, petitionUserId);
            }

            if (notReadedCnt > Constants.NUM_0) {
                returnResult.setMsgCount(notReadedCnt);
                returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
            } else {
                returnResult.setMsgCount(Constants.NUM_0);
            }
            return returnResult;
        }

        return null;
    }

    /**
     * テーブルより下書き保存のデータを取得する。
     *
     * @param uid ユーザID
     * @return 申立て登録下書き保存データ有無
     */
    @Override
    public Integer getSaveDataInfo(String uid) {
        
        // 関連ユーザの下書き保存のデータを取得する
        DraftSavingDate draftSavingDate = getSaveDataInfoMapper.getSaveDataInfo(uid);
        
        // 必須項目が存在するかどうかを判断する
        if (draftSavingDate != null && requiredItemIsNull(draftSavingDate)) {
            return Constants.REGISTRATION_REGISTRATION;
        } else {
            return Constants.REGISTRATION_REGISTRATION_UNREGISTERED;
        }
    }

    /**
     * 申立て登録下書き保存データ有無の判定
     *
     * @param draftSavingDate サービスの呼び出し
     * @return 申立て登録下書き保存データ有無
     */
    private boolean requiredItemIsNull(DraftSavingDate draftSavingDate) {
        if (draftSavingDate.getTraderUserEmail() != null
                && draftSavingDate.getProductName() != null
                && draftSavingDate.getBoughtDate() != null
                && draftSavingDate.getPrice() != null
                && draftSavingDate.getPetitionTypeValue() != null
                && draftSavingDate.getPetitionContext() != null
                && draftSavingDate.getExpectResloveTypeValue() != null) {
            return true;
        } else {
            return false;
        }
    }
}
