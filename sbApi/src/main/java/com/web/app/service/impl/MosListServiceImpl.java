package com.web.app.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.MosDetail.UserCase;
import com.web.app.domain.MosList.CaseDetailCasesSelectInfo;
import com.web.app.domain.MosList.CaseIdListInfo;
import com.web.app.domain.MosList.DraftSavingDate;
import com.web.app.domain.MosList.Position;
import com.web.app.domain.MosList.ReturnResult;
import com.web.app.domain.MosList.SearchDetail;
import com.web.app.domain.MosList.SelectCondition;
import com.web.app.domain.MosList.SelectUserInfoForCase;
import com.web.app.domain.MosList.TestMos;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.FuzzyQueryDetailCaseMapper;
import com.web.app.mapper.GetCaseDetailMapper;
import com.web.app.mapper.GetFuzzyQueryListInfoMapper;
import com.web.app.mapper.GetListInfoMapper;
import com.web.app.mapper.GetSaveDataInfoMapper;
import com.web.app.mapper.GetSelectListInfoMapper;
import com.web.app.mapper.SearchDetailCaseMapper;
import com.web.app.service.MosListService;

/**
 * 申立て一覧画面
 * 
 * @author DUC 張万超 馮淑慧 王魯興
 * @since 2024/4/22
 * @version 1.0
 */
@Service
public class MosListServiceImpl implements MosListService {

    private static final Logger log = LogManager.getLogger(MosListServiceImpl.class);

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

    @Autowired
    private GetCaseDetailMapper getCaseDetailMapper;

    @Autowired
    private GetSelectListInfoMapper getSelectListInfoMapper;

    @Autowired
    private GetListInfoMapper getListInfoMapper;

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
            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            returnResult.setCorrespondDate(Constants.DEFAULT_CORRESPONDDATE);
            // 取得したCaseStageの値を判定して
            try {
                switch (searchDetail.getCaseStage()) {
                    case Constants.STR_CASES_CASESTAGE_0:
                        returnResult.setCorrespondDate(
                                simpleDateFormat.format(originalFormat.parse(searchDetail.getReplyEndDate())));
                        break;
                    case Constants.STR_CASES_CASESTAGE_2:
                        returnResult.setCorrespondDate(
                                simpleDateFormat.format(originalFormat.parse(searchDetail.getConuterclaimEndDate())));
                        break;
                    case Constants.STR_CASES_CASESTAGE_3:
                        returnResult.setCorrespondDate(
                                simpleDateFormat.format(originalFormat.parse(searchDetail.getNegotiationEndDate())));
                        break;
                    case Constants.STR_CASES_CASESTAGE_6:
                    case Constants.STR_CASES_CASESTAGE_7:
                        returnResult.setCorrespondDate(
                                simpleDateFormat.format(originalFormat.parse(searchDetail.getMediationEndDate())));
                        break;
                    default:
                        returnResult.setCorrespondDate(Constants.DEFAULT_CORRESPONDDATE);
                }
            } catch (ParseException e) {
                log.error(Constants.DATATIME_FORMAT_ERROR);
            } catch (NullPointerException e) {
                log.error(Constants.NULL_ERROR);
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
                        if ((searchDetail.getNegotiationEndDateChangeStatus() != null
                                && searchDetail.getNegotiationEndDateChangeStatus() == Constants.NUM_1)
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
                        if ((searchDetail.getMediationsStatus() != null
                                && (searchDetail.getMediationsStatus() == Constants.NUM_1
                                        || searchDetail.getMediationsStatus() == Constants.NUM_3
                                        || searchDetail.getMediationsStatus() == Constants.NUM_8))
                                || (searchDetail.getGroupMessageFlag2() != null
                                        && searchDetail.getGroupMessageFlag2() == Constants.NUM_1)) {
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
                        if ((searchDetail.getNegotiationEndDateChangeStatus() != null
                                && searchDetail.getNegotiationEndDateChangeStatus() == Constants.NUM_2)
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
                        if ((searchDetail.getMediationsStatus() != null
                                && (searchDetail.getMediationsStatus() == Constants.NUM_1
                                        || searchDetail.getMediationsStatus() == Constants.NUM_2
                                        || searchDetail.getMediationsStatus() == Constants.NUM_7))
                                || (searchDetail.getGroupMessageFlag1() != null
                                        && searchDetail.getGroupMessageFlag1() == Constants.NUM_1)) {
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
                        if (searchDetail.getMediationsStatus() == null
                                || searchDetail.getMediationsStatus() == Constants.NUM_0) {
                            // 要対応有り
                            returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        }
                        break;
                    default:
                        // 要対応なし
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_0);
                        break;
                }
            } else {
                log.error(Constants.PARAMETER_ERROR);
                return null;
            }

            // 未読メッセージ件数はデフォルトで0
            int notReadedCnt = Constants.NUM_0;
            // 未読メッセージ件数の取得
            if (searchCase.getPositionFlg() == Constants.POSITIONFLAG_MEDIATOR) {
                // ステジ：6調停者指名中（未受理の場合）以外
                if (searchDetail.getCaseStage() != 6) {
                    Integer mediatorDisclosureFlag = searchDetailCaseMapper
                            .getMediatorDisclosureFlag(searchCase.getCaseId());
                    if (mediatorDisclosureFlag != null && mediatorDisclosureFlag == Constants.NUM_1) {
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
            try {
                returnResult
                        .setPetitionDate(simpleDateFormat.format(originalFormat.parse(searchDetail.getPetitonDate())));
            } catch (ParseException e) {
                returnResult
                        .setPetitionDate(Constants.DEFAULT_CORRESPONDDATE);
                log.error(Constants.DATATIME_FORMAT_ERROR);
            }
            returnResult.setCid(searchDetail.getCid());
            returnResult.setCaseTitle(searchDetail.getCaseTitle());
            returnResult.setCaseStatus(Integer.toString(searchDetail.getCaseStage()));
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
     * @param caseId         CaseId
     * @param petitionUserId case申立て人
     * @param positionFlag   立場フラグ
     * @param queryString    画面.検索Box入力文字列
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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // データアセンブリ
            try {
                returnResult.setPetitionDate(
                        simpleDateFormat.format(originalFormat.parse(queryDetailCase.getPetitonDate())));
            } catch (ParseException e) {
                log.error(Constants.DATATIME_FORMAT_ERROR);
                returnResult.setPetitionDate(Constants.DEFAULT_CORRESPONDDATE);
            }
            returnResult.setCid(queryDetailCase.getCid());
            returnResult.setCaseTitle(queryDetailCase.getCaseTitle());
            returnResult.setCaseStatus(Integer.toString(queryDetailCase.getCaseStage()));

            // 対応期日の設定
            returnResult.setCorrespondDate(Constants.DEFAULT_CORRESPONDDATE);
            try {
                switch (queryDetailCase.getCaseStage()) {
                    case Constants.STR_CASES_CASESTAGE_0:
                        returnResult.setCorrespondDate(
                                simpleDateFormat.format(originalFormat.parse(queryDetailCase.getReplyEndDate())));
                        break;
                    case Constants.STR_CASES_CASESTAGE_2:
                        returnResult
                                .setCorrespondDate(simpleDateFormat
                                        .format(originalFormat.parse(queryDetailCase.getConuterclaimEndDate())));
                        break;
                    case Constants.STR_CASES_CASESTAGE_3:
                        returnResult
                                .setCorrespondDate(simpleDateFormat
                                        .format(originalFormat.parse(queryDetailCase.getNegotiationEndDate())));
                        break;
                    case Constants.STR_CASES_CASESTAGE_6:
                    case Constants.STR_CASES_CASESTAGE_7:
                        returnResult.setCorrespondDate(simpleDateFormat
                                .format(originalFormat.parse(queryDetailCase.getMediationEndDate())));
                        break;
                    default:
                        returnResult.setCorrespondDate(Constants.DEFAULT_CORRESPONDDATE);
                }
            } catch (ParseException e) {
                log.error(Constants.DATATIME_FORMAT_ERROR);
            } catch (NullPointerException e) {
                log.error(Constants.NULL_ERROR);
            }

            // 要対応有無の設定
            returnResult.setCorrespondence(Constants.CORRESPONDENCE_0);
            if (positionFlag == Constants.POSITIONFLAG_PETITION) {
                switch (queryDetailCase.getCaseStage()) {
                    case Constants.STR_CASES_CASESTAGE_2:
                        returnResult.setCorrespondence(Constants.CORRESPONDENCE_1);
                        break;
                    case Constants.STR_CASES_CASESTAGE_3:
                        if ((queryDetailCase.getNegotiationEndDateChangeStatus() != null
                                && queryDetailCase.getNegotiationEndDateChangeStatus() == Constants.NUM_1)
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
                        if ((queryDetailCase.getMediationsStatus() != null
                                && (queryDetailCase.getMediationsStatus() == Constants.NUM_1
                                        || queryDetailCase.getMediationsStatus() == Constants.NUM_3
                                        || queryDetailCase.getMediationsStatus() == Constants.NUM_8))
                                || (queryDetailCase.getGroupMessageFlag2() != null
                                        && queryDetailCase.getGroupMessageFlag2() == Constants.NUM_1)) {
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
                        if ((queryDetailCase.getNegotiationEndDateChangeStatus() != null
                                && queryDetailCase.getNegotiationEndDateChangeStatus() == Constants.NUM_2)
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
                        if ((queryDetailCase.getMediationsStatus() != null
                                && (queryDetailCase.getMediationsStatus() == Constants.NUM_1
                                        || queryDetailCase.getMediationsStatus() == Constants.NUM_2
                                        || queryDetailCase.getMediationsStatus() == Constants.NUM_7))
                                || (queryDetailCase.getGroupMessageFlag1() != null
                                        && queryDetailCase.getGroupMessageFlag1() == Constants.NUM_1)) {
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
            } else {
                log.error(Constants.PARAMETER_ERROR);
                return null;
            }

            // 未読メッセージ件数はデフォルトで0
            int notReadedCnt = Constants.NUM_0;
            // 未読メッセージ件数の取得
            if (positionFlag == Constants.POSITIONFLAG_MEDIATOR) {
                // ステジ：6調停者指名中（未受理の場合）以外
                if (queryDetailCase.getCaseStage() != Constants.NUM_6) {
                    Integer mediatorDisclosureFlag = searchDetailCaseMapper
                            .getMediatorDisclosureFlag(caseId);
                    if (mediatorDisclosureFlag != null && mediatorDisclosureFlag == Constants.NUM_1) {
                        notReadedCnt = searchDetailCaseMapper.getMsgCountByFlag(caseId,
                                petitionUserId);
                    } else {
                        notReadedCnt = searchDetailCaseMapper.getMsgCountByFlagNo(caseId,
                                petitionUserId);
                    }
                }
            } else {
                notReadedCnt = searchDetailCaseMapper.getMsgCountByFlag(caseId,
                        petitionUserId);
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

    /**
     * ケース詳細取得
     *
     * @param caseIdListInfo API「 一覧取得」より渡された引数
     * @return 戻り値はAPI「 一覧取得」に返される
     */
    @Override
    public ReturnResult caseDetailCasesInfoSearch(CaseIdListInfo caseIdListInfo) {
        // 案件ID
        String caseId = caseIdListInfo.getCaseId();
        // 立場フラグ
        Integer idFlag = caseIdListInfo.getFlag();
        // ユーザーID
        String userId = caseIdListInfo.getUserId();
        // 「cases」から申立て番号、件名、登録日付、対応期日、状態、要対応有無を取得した
        // 「case_negotiations」から「ステータス」を取得した
        // 「case_mediations」から「ステータス」を取得した
        CaseDetailCasesSelectInfo caseDetailCasesInfo = getCaseDetailMapper.caseDetailCasesInforSearch(caseId);

        if (caseDetailCasesInfo != null) {
            // ケース詳細調停案で「case_mediations」を取得
            ReturnResult caseInfo = caseDetailMediationsInfoSearch(caseDetailCasesInfo, caseId, idFlag, userId);
            return caseInfo;
        } else {
            return null;
        }
    }

    /**
     * ケース詳細取得設定
     *
     * @param caseDetailCasesSelInfo 取得したの申立て番号、件名、登録日付、対応期日、状態、要対応有無
     * @param caseId                 API「 一覧取得」より渡された引数: 案件ID
     * @param idFlag                 API「 一覧取得」より渡された引数: 立場フラグ
     * @param userId                 API「 一覧取得」より渡された引数: ユーザーID
     * @return 戻り値はAPI「 一覧取得」に返される
     */
    private ReturnResult caseDetailMediationsInfoSearch(CaseDetailCasesSelectInfo caseDetailCasesSelInfo,
            String caseId, Integer idFlag, String userId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // 戻り値
        ReturnResult caseDetailCasesInfoItem = new ReturnResult();
        // 戻り値の申立て番号に「cases」から「ID」を取得したを設定された
        caseDetailCasesInfoItem.setCid(caseDetailCasesSelInfo.getCId());
        // 戻り値の状態に「cases」から「案件ステージ」を取得したを設定された
        caseDetailCasesInfoItem.setCaseStatus(caseDetailCasesSelInfo.getCaseStage().toString());
        // 戻り値の件名に「cases」から「タイトル名」を取得したを設定された
        caseDetailCasesInfoItem.setCaseTitle(caseDetailCasesSelInfo.getCaseTitle());
        // 戻り値の登録日付に「cases」から「申立て日」を取得したを設定された
        caseDetailCasesInfoItem.setPetitionDate(sdf.format(caseDetailCasesSelInfo.getPetitionDate()));

        // 戻り値の対応期日を設定された
        // オブジェクトを作成する
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT);
        // 対応期日初期値
        String dateString;
        // 「cases」から「案件ステージ」を取得したの判定：0:（申立）
        if (caseDetailCasesSelInfo.getCaseStage() == Constants.STR_CASES_CASESTAGE_0) {
            // 対応期日を文字列にフォーマットを設定する
            dateString = simpleDateFormat.format(caseDetailCasesSelInfo.getReplyEndDate());
        } else if (caseDetailCasesSelInfo.getCaseStage() == Constants.STR_CASES_CASESTAGE_2) {
            // 「cases」から「案件ステージ」を取得したの判定：2:（反訴）
            // 対応期日を文字列にフォーマットを設定する
            dateString = simpleDateFormat.format(caseDetailCasesSelInfo.getCounterclaimEndDate());
        } else if (caseDetailCasesSelInfo.getCaseStage() == Constants.STR_CASES_CASESTAGE_3) {
            // 「cases」から「案件ステージ」を取得したの判定：3:（交渉）
            // 対応期日を文字列にフォーマットを設定する
            dateString = simpleDateFormat.format(caseDetailCasesSelInfo.getNegotiationEndDate());
        } else if (caseDetailCasesSelInfo.getCaseStage() == Constants.STR_CASES_CASESTAGE_6
                || caseDetailCasesSelInfo.getCaseStage() == Constants.STR_CASES_CASESTAGE_7) {
            // 「cases」から「案件ステージ」を取得したの判定：6:（調停人指名）7:（調停）
            // 対応期日を文字列にフォーマットを設定するする
            dateString = simpleDateFormat.format(caseDetailCasesSelInfo.getMediationEndDate());
        } else {
            dateString = Constants.DEFAULT_CORRESPONDDATE;
        }
        // 対応期日を設定された
        caseDetailCasesInfoItem.setCorrespondDate(dateString);

        // 要対応有無の設定
        // 立場フラグが1（申立人）の場合
        if (idFlag == Constants.POSITIONFLAG_PETITION) {
            // 戻り値の立場フラグを設定された
            caseDetailCasesInfoItem.setPositionFlg(idFlag);

            // 「cases」から取得したの「案件ステージ」の比較
            switch (caseDetailCasesSelInfo.getCaseStage()) {
                case Constants.STR_CASES_CASESTAGE_2:
                    // 要対応有無に1（要対応）を設定する
                    caseDetailCasesInfoItem.setCorrespondence(Constants.CORRESPOND_FLAG_1);
                    break;
                case Constants.STR_CASES_CASESTAGE_3:
                    // 「cases」から取得したの「交渉期限日変更ステータス」の比較
                    // 「case_negotiations」から取得したの「ステータス」の比較
                    if (caseDetailCasesSelInfo
                            .getNegotiationEndDateChangeStatus() == Constants.STR_CASES_NEGOTIATIONENDDATECHANGESTATUS_1
                            ||
                            (caseDetailCasesSelInfo.getCaseNegotiationsStatus() == null
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_1
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_2
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_3
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_5
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_7
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_8
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_12
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_13
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_14)) {
                        // 要対応有無に1（要対応）を設定する
                        caseDetailCasesInfoItem.setCorrespondence(Constants.CORRESPOND_FLAG_1);
                    }
                    break;
                case Constants.STR_CASES_CASESTAGE_6:
                    // 要対応有無に1（要対応）を設定する
                    caseDetailCasesInfoItem.setCorrespondence(Constants.CORRESPOND_FLAG_1);
                    break;
                case Constants.STR_CASES_CASESTAGE_7:
                    // 「case_mediations」から「ステータス」取得した
                    Integer mediationsInfoStatus = caseDetailCasesSelInfo.getMediationsStatus();
                    // 「case_mediations」から「ステータス」を取得したの比較
                    if (mediationsInfoStatus != null
                            && (mediationsInfoStatus == Constants.STR_CASE_MEDIATIONS_STATUS_1
                                    || mediationsInfoStatus == Constants.STR_CASE_MEDIATIONS_STATUS_3
                                    || mediationsInfoStatus == Constants.STR_CASE_MEDIATIONS_STATUS_8)
                            || (caseDetailCasesSelInfo.getGroupMessageFlag2() != null && caseDetailCasesSelInfo
                                    .getGroupMessageFlag2() == Constants.STR_CASES_GROUPMESSAGEFLAG)) {
                        // 要対応有無に1（要対応）を設定する
                        caseDetailCasesInfoItem.setCorrespondence(Constants.CORRESPOND_FLAG_1);
                    }
                    break;
                default:
                    // 上記以外の場合、要対応有無に0（要対応なし）を設定する
                    caseDetailCasesInfoItem.setCorrespondence(Constants.CORRESPOND_FLAG_1);
            }
        } else if (idFlag == Constants.POSITIONFLAG_TRADER) {
            // 立場フラグが2（相手方）の場合
            // 戻り値の立場フラグを設定された
            caseDetailCasesInfoItem.setPositionFlg(idFlag);

            // 「cases」から取得したの「案件ステージ」の比較
            switch (caseDetailCasesSelInfo.getCaseStage()) {
                case Constants.STR_CASES_CASESTAGE_0:
                    // 要対応有無に1（要対応）を設定する
                    caseDetailCasesInfoItem.setCorrespondence(Constants.CORRESPOND_FLAG_1);
                    break;
                case Constants.STR_CASES_CASESTAGE_3:
                    // 「cases」から取得したの「交渉期限日変更ステータス」の比較
                    // 「case_negotiations」から取得したの「ステータス」の比較
                    if (caseDetailCasesSelInfo
                            .getNegotiationEndDateChangeStatus() == Constants.STR_CASES_NEGOTIATIONENDDATECHANGESTATUS_2
                            ||
                            (caseDetailCasesSelInfo.getCaseNegotiationsStatus() == null ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_0
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_1
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_3
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_4
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_9
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_10
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_11
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_14
                                    ||
                                    caseDetailCasesSelInfo
                                            .getCaseNegotiationsStatus() == Constants.STR_CASE_NEGOTIATIONS_STATUS_15)) {
                        // 要対応有無に1（要対応）を設定する
                        caseDetailCasesInfoItem.setCorrespondence(Constants.CORRESPOND_FLAG_1);
                    }
                    break;
                case Constants.STR_CASES_CASESTAGE_6:
                    // 要対応有無に1（要対応）を設定する
                    caseDetailCasesInfoItem.setCorrespondence(Constants.CORRESPOND_FLAG_0);
                    break;
                case Constants.STR_CASES_CASESTAGE_7:
                    // 「case_mediations」から「ステータス」を取得した
                    Integer mediationsInfoStatus = caseDetailCasesSelInfo.getMediationsStatus();
                    // 「case_mediations」から「ステータス」を取得したの比較
                    if (mediationsInfoStatus != null
                            && (mediationsInfoStatus == Constants.STR_CASE_MEDIATIONS_STATUS_1
                            || mediationsInfoStatus == Constants.STR_CASE_MEDIATIONS_STATUS_2
                            || mediationsInfoStatus == Constants.STR_CASE_MEDIATIONS_STATUS_7)
                            || (caseDetailCasesSelInfo.getGroupMessageFlag1() != null
                            &&  caseDetailCasesSelInfo.getGroupMessageFlag1() == Constants.STR_CASES_GROUPMESSAGEFLAG)) {
                        // 要対応有無に1（要対応）を設定する
                        caseDetailCasesInfoItem.setCorrespondence(Constants.CORRESPOND_FLAG_1);
                    }
                    break;
                default:
                    // 上記以外の場合、要対応有無に0（要対応なし）を設定する
                    caseDetailCasesInfoItem.setCorrespondence(Constants.CORRESPOND_FLAG_1);
            }
        } else if (idFlag == Constants.POSITIONFLAG_MEDIATOR) {
            // 立場フラグが3（調停人）の場合
            // 戻り値の立場フラグを設定された
            caseDetailCasesInfoItem.setPositionFlg(idFlag);

            // 要対応有無の設定
            // 「cases」から取得したの「案件ステージ」の比較
            switch (caseDetailCasesSelInfo.getCaseStage()) {
                case Constants.STR_CASES_CASESTAGE_6:
                    // 要対応有無に1（要対応）を設定する
                    caseDetailCasesInfoItem.setCorrespondence(Constants.CORRESPOND_FLAG_1);
                    break;
                case Constants.STR_CASES_CASESTAGE_7:
                    // 「case_mediations」から取得した
                    Integer mediationsInfoStatus = caseDetailCasesSelInfo.getMediationsStatus();
                    if (mediationsInfoStatus == null || mediationsInfoStatus == 0) {
                        // 要対応有無に1（要対応）を設定する
                        caseDetailCasesInfoItem.setCorrespondence(Constants.CORRESPOND_FLAG_1);
                    }
                    break;
                default:
                    // 上記以外の場合、要対応有無に0（要対応なし）を設定する
                    caseDetailCasesInfoItem.setCorrespondence(Constants.CORRESPOND_FLAG_1);
            }

            // 未読メッセージ件数の取得
            if (caseDetailCasesSelInfo.getCaseStage() == Constants.STR_CASES_CASESTAGE_6) {
                // 1）未読メッセージ件数取得
                Integer messagesReadedCnt = Constants.NOREADCNT_0;
                // 「cases」から「調停人情報開示フラグ」を取得
                Integer mediatorDisclosureFlagItem = getCaseDetailMapper.getMediatorDisclosureFlagInfoSearch(caseId);

                // 未読メッセージ取得（申立人・相手方・調停人受理開示後）
                if (mediatorDisclosureFlagItem == Constants.STR_CASES_MEDIATORDISCLOSUREFLAG_1) {
                    // b ;未読メッセージ取得（申立人・相手方・調停人受理開示後）
                    messagesReadedCnt = getCaseDetailMapper.getMessagesReadCntSearch(caseId, userId);

                    // 2）未読メッセージ有無の設定
                    if (messagesReadedCnt > Constants.NOREADCNT_0) {
                        // 未読メッセージ件数の設定
                        caseDetailCasesInfoItem.setMsgCount(messagesReadedCnt);
                        // 3）要対応有無の設定（メッセージ向け）
                        // 要対応有無に1（要対応）を設定する
                        caseDetailCasesInfoItem.setCorrespondence(Constants.CORRESPOND_FLAG_1);
                    } else {
                        // 未読メッセージ件数の設定
                        caseDetailCasesInfoItem.setMsgCount(Constants.NOREADCNT_0);
                    }
                } else {
                    // 未読メッセージ件数取得 （調停人＋受理後＋未開示）
                    // A.3.
                    Integer userMessagesReadCnt = getCaseDetailMapper.getUserMessagesReadCntSearch(caseId, userId);

                    // 2）未読メッセージ有無の設定
                    if (userMessagesReadCnt > Constants.NOREADCNT_0) {
                        // 未読メッセージ件数の設定
                        caseDetailCasesInfoItem.setMsgCount(userMessagesReadCnt);
                        // 3）要対応有無の設定（メッセージ向け）
                        // 要対応有無に1（要対応）を設定する
                        caseDetailCasesInfoItem.setCorrespondence(Constants.CORRESPOND_FLAG_1);
                    } else {
                        // 未読メッセージ件数の設定
                        caseDetailCasesInfoItem.setMsgCount(Constants.NOREADCNT_0);
                    }
                }
            }
        }
        // 未読メッセージ件数の取得
        // b ;未読メッセージ取得（申立人・相手方・調停人受理開示後）
        Integer messagesReadedCnt = getCaseDetailMapper.getMessagesReadCntSearch(caseId, userId);

        // 2）未読メッセージ有無の設定
        if (messagesReadedCnt > Constants.NOREADCNT_0) {
            // 未読メッセージ件数の設定
            caseDetailCasesInfoItem.setMsgCount(messagesReadedCnt);
            // 3）要対応有無の設定（メッセージ向け）
            // 要対応有無に1（要対応）を設定する
            caseDetailCasesInfoItem.setCorrespondence(Constants.CORRESPOND_FLAG_1);
        } else {
            // 未読メッセージ件数の設定
            caseDetailCasesInfoItem.setMsgCount(Constants.NOREADCNT_0);
        }
        return caseDetailCasesInfoItem;
    }

    /**
     * 検索条件の引数によって、一覧データを取得する。
     *
     * @param position 検索サブ画面で入力の画面項目
     * @return ReturnResult 一覧画面表示用のデータ
     */
    @Override
    public List<ReturnResult> getSelectListInfo(Position position) {

        // 共通関数「申立人のケース取得」の戻り内容
        List<ReturnResult> petitionsResult = new ArrayList<>();
        // 共通関数「相手方のケース取得」の戻り内容
        List<ReturnResult> traderFlgResult = new ArrayList<>();
        // 共通関数「調停人のケース取得」の戻り内容
        List<ReturnResult> mediatorResult = new ArrayList<>();

        // 1.TBL「ユーザ情報」を取得する
        String email = getSelectListInfoMapper.testOdrUsersSearch(position.getSessionId());

        // TBL「ユーザ情報」取得なしの場合、nullが返される。
        // 以外の場合、TBL「ユーザ情報」より取得したユーザメールをキーにTBL「案件別個人情報リレーション」より申立人、相手方、調停人となるケース取得
        if (email == null) {
            return null;
        }
        // 引数.立場申立人Flg=0 かつ 引数.立場相手方Flg=0 かつ 引数.立場調停人Flg=0 の場合、申立人、相手方、調停人の取得処理を全て行う。
        if (position.getPetitionsFlg1() == 0 && position.getTraderFlg2() == 0 && position.getMediatorFlg3() == 0) {

            // 共通関数「申立人のケース取得」
            petitionsResult = getPetitions(email, position);

            // 共通関数「相手方のケース取得」
            traderFlgResult = getTraderFlg(email, position);

            // 共通関数「調停人のケース取得」
            mediatorResult = getMediator(email, position);
        }
        // 以外の場合
        else {
            // 引数.立場申立人Flg＝1の場合、申立人の取得処理を行う
            if (position.getPetitionsFlg1() == Constants.NUM_1) {
                // 共通関数「申立人のケース取得」
                petitionsResult = getPetitions(email, position);
            }
            // 引数.立場相手方Flg＝1の場合、相手方の取得処理を行う
            if (position.getTraderFlg2() == Constants.NUM_1) {
                // 共通関数「相手方のケース取得」
                traderFlgResult = getTraderFlg(email, position);
            }
            // 引数.立場調停人Flg＝1の場合、調停人の取得処理を行う
            if (position.getMediatorFlg3() == Constants.NUM_1) {
                // 共通関数「調停人のケース取得」
                mediatorResult = getMediator(email, position);
            }
        }

        // 申立人２次元配列（もしくはリスト）、相手方２次元配列（もしくはリスト）、調停人２次元配列（もしくはリスト）を結合する。
        List<ReturnResult> preResult = new ArrayList<>(petitionsResult);
        preResult.addAll(traderFlgResult);
        preResult.addAll(mediatorResult);

        for (int i = preResult.size() - 1; i >= 0; i--) {
            // ・引数.メッセージ有無Flgによるデータ抽出結合
            // a.引数.メッセージ有Flgが1の場合、未読メッセージ件数 > 0件のデータを抽出して、結合する。
            // b.引数.メッセージ無Flgが1の場合、未読メッセージ件数 = 0件のデータを抽出して、結合する。
            // c.上記以外の場合、両方のデータとも抽出して結合する
            if (position.getMessageFlag() == "1") {
                if (preResult.get(i).getMsgCount() <= 0) {
                    preResult.remove(i);
                }
            } else if (position.getMessageFlag() == "2") {
                if (preResult.get(i).getMsgCount() != 0) {
                    preResult.remove(i);
                }
            }
            // ・引数.要対応有無Flgによるデータ抽出結合
            // a.引数.要対応有Flgが1の場合、要対応有無が1（要対応有り）のデータを抽出して、結合する。
            // b.引数.要対応無FlgFlgが1の場合、要対応有無が0（要対応なし）のデータを抽出して、結合する。
            // c.上記以外の場合、両方のデータとも抽出して結合する
            if (position.getCorrespondenceFlag() == "1") {
                if (preResult.get(i).getCorrespondence() != "1") {
                    preResult.remove(i);
                }
            } else if (position.getCorrespondenceFlag() == "2") {
                if (preResult.get(i).getCorrespondence() != "0") {
                    preResult.remove(i);
                }
            }
        }

        // 要対応有無の降順 かつ 対応期日の昇順で結合後の２次元配列（もしくはリスト）をソートする
        if (preResult.size() > 0) {
            List<ReturnResult> finalResult = preResult.stream()
                    .sorted(Comparator.comparing(ReturnResult::getCorrespondence)
                            .reversed().thenComparing(ReturnResult::getCorrespondDate))
                    .collect(Collectors.toList());

            return (finalResult);
        } else {
            return (preResult);
        }
    }

    /**
     * 共通関数「申立人のケース取得」
     *
     * @param email    ユーザ情報.Email
     * @param position 検索サブ画面で入力の画面項目
     * @return ReturnResult 一覧画面表示用の申立人のデータ
     */
    private List<ReturnResult> getPetitions(String email, Position position) {

        // 申立人のケース内容取得List
        List<TestMos> list1 = new ArrayList<>();
        // 申立人のケース詳細内容取得List
        List<ReturnResult> petitionsResults = new ArrayList<>();
        // API「検索用ケース詳細取得」の検索条件の引数
        SelectCondition selectCondition1 = new SelectCondition();

        // ⓵申立人の取得処理を行う。
        list1 = getSelectListInfoMapper.testPetitionsSearch(email);

        // 申立人取得有りの場合、下記処理を行う）
        if (list1.size() > 0) {
            for (int i = 0; i < list1.size(); i++) {

                // ⓵取得したCaseId
                selectCondition1.setCaseId(list1.get(i).getCaseId());
                // ⓵取得したPetitionUserId
                selectCondition1.setPetitionUserId(list1.get(i).getPetitionUserId());
                // 引数.立場フラグ(立場フラグ＝1（申立人）)
                selectCondition1.setPositionFlg(Constants.POSITIONFLAG_PETITION);
                // 引数.申立て番号
                selectCondition1.setCid(position.getCid());
                // 引数.件名
                selectCondition1.setCaseTitle(position.getCaseTitle());
                // 引数.登録日付From
                selectCondition1.setPetitionDateStart(position.getPetitionDateStart());
                // 引数.登録日付To
                selectCondition1.setPetitionDateEnd(position.getPetitionDateEnd());
                // 引数.ステータス
                selectCondition1.setCaseStatus(position.getCaseStatus());

                // API「検索用ケース詳細取得」
                ReturnResult resultList1 = searchDetailCase(selectCondition1);
                // 戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                if (resultList1 != null && resultList1.getCid() != null) {
                    petitionsResults.add(resultList1);
                }
            }
        }
        return petitionsResults;
    }

    /**
     * 共通関数「相手方のケース取得」
     *
     * @param email    ユーザ情報.Email
     * @param position 検索サブ画面で入力の画面項目
     * @return ReturnResult 一覧画面表示用の相手方のデータ
     */
    private List<ReturnResult> getTraderFlg(String email, Position position) {

        // 相手方のケース内容取得List
        List<TestMos> list2 = new ArrayList<>();
        // 相手方のケース詳細内容取得List
        List<ReturnResult> traderFlgResults = new ArrayList<>();
        // API「検索用ケース詳細取得」の検索条件の引数
        SelectCondition selectCondition2 = new SelectCondition();

        // ⓶相手方の取得処理を行う。
        list2 = getSelectListInfoMapper.testTraderFlgSearch(email);

        // 相手方取得有りの場合、下記処理を行う）
        if (list2.size() > 0) {
            for (int i = 0; i < list2.size(); i++) {

                // ⓶取得したCaseId
                selectCondition2.setCaseId(list2.get(i).getCaseId());
                // ⓶取得したPetitionUserId
                selectCondition2.setPetitionUserId(list2.get(i).getPetitionUserId());
                // 引数.立場フラグ(立場フラグ＝2（相手方）)
                selectCondition2.setPositionFlg(Constants.POSITIONFLAG_TRADER);
                // 引数.申立て番号
                selectCondition2.setCid(position.getCid());
                // 引数.件名
                selectCondition2.setCaseTitle(position.getCaseTitle());
                // 引数.登録日付From
                selectCondition2.setPetitionDateStart(position.getPetitionDateStart());
                // 引数.登録日付To
                selectCondition2.setPetitionDateEnd(position.getPetitionDateEnd());
                // 引数.ステータス
                selectCondition2.setCaseStatus(position.getCaseStatus());

                // API「検索用ケース詳細取得」
                ReturnResult resultList2 = searchDetailCase(selectCondition2);
                // 戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                if (resultList2 != null && resultList2.getCid() != null) {
                    traderFlgResults.add(resultList2);
                }
            }
        }
        return traderFlgResults;
    }

    /**
     * 共通関数「調停人のケース取得」
     *
     * @param email    ユーザ情報.Email
     * @param position 検索サブ画面で入力の画面項目
     * @return ReturnResult 一覧画面表示用の調停人のデータ
     */
    private List<ReturnResult> getMediator(String email, Position position) {

        // 調停人のケース内容取得List
        List<TestMos> list3 = new ArrayList<>();
        // 調停人のケース詳細内容取得List
        List<ReturnResult> mediatorResults = new ArrayList<>();
        // API「検索用ケース詳細取得」の検索条件の引数
        SelectCondition selectCondition3 = new SelectCondition();

        // ⓷調停人の取得処理を行う。
        list3 = getSelectListInfoMapper.testMediatorSearch(email);

        // 調停人取得有りの場合、下記処理を行う）
        if (list3.size() > 0) {
            for (int i = 0; i < list3.size(); i++) {

                // ⓷取得したCaseId
                selectCondition3.setCaseId(list3.get(i).getCaseId());
                // ⓷取得したPetitionUserId
                selectCondition3.setPetitionUserId(list3.get(i).getPetitionUserId());
                // 引数.立場フラグ(立場フラグ＝3（調停人）)
                selectCondition3.setPositionFlg(Constants.POSITIONFLAG_MEDIATOR);
                // 引数.申立て番号
                selectCondition3.setCid(position.getCid());
                // 引数.件名
                selectCondition3.setCaseTitle(position.getCaseTitle());
                // 引数.登録日付From
                selectCondition3.setPetitionDateStart(position.getPetitionDateStart());
                // 引数.登録日付To
                selectCondition3.setPetitionDateEnd(position.getPetitionDateEnd());
                // 引数.ステータス
                selectCondition3.setCaseStatus(position.getCaseStatus());

                // API「検索用ケース詳細取得」
                ReturnResult resultList3 = searchDetailCase(selectCondition3);
                // 戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                if (resultList3 != null && resultList3.getCid() != null) {
                    mediatorResults.add(resultList3);
                }
            }
        }
        return mediatorResults;
    }

    /**
     * 一覧取得
     *
     * @param uid ユーザID
     * @return API_一覧取得の取得内容
     */
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
        CaseIdListInfo caseDetails1 = new CaseIdListInfo();
        // 立場フラグ=1
        caseDetails1.setFlag(Constants.STR_CASE_STAGE_REMOVE);
        // セッション.ユーザID
        caseDetails1.setUserId(uid);
        // ユーザ情報
        if (caseIdPetition1.size() > Constants.STR_CASE_STAGE_REPLY) {
            for (int i = 0; i < caseIdPetition1.size(); i++) {
                // ①取得したCaseId
                caseDetails1.setCaseId(caseIdPetition1.get(i).getCaseId());
                // ①取得したPetitionUserId
                caseDetails1.setPetitionUserId(caseIdPetition1.get(i).getPetitionUserId());
                // API「ケース詳細取得」を呼び出す
                ReturnResult returnResult1 = caseDetailCasesInfoSearch(caseDetails1);
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
        CaseIdListInfo caseDetails2 = new CaseIdListInfo();
        // 立場フラグ=2
        caseDetails2.setFlag(Constants.STR_CASE_STAGE_CLAIMREPLY);
        // セッション.ユーザID
        caseDetails2.setUserId(uid);
        // ユーザ情報
        if (caseIdPetition2.size() > Constants.STR_CASE_STAGE_REPLY) {
            for (int i = 0; i < caseIdPetition2.size(); i++) {
                // ①取得したCaseId
                caseDetails2.setCaseId(caseIdPetition2.get(i).getCaseId());
                // ①取得したPetitionUserId
                caseDetails2.setPetitionUserId(caseIdPetition2.get(i).getPetitionUserId());
                // API「ケース詳細取得」を呼び出す
                ReturnResult returnResult2 = caseDetailCasesInfoSearch(caseDetails2);
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
        CaseIdListInfo caseDetails3 = new CaseIdListInfo();
        // 立場フラグ=3
        caseDetails3.setFlag(Constants.STR_CASE_STAGE_NEGOTIATION);
        // セッション.ユーザID
        caseDetails3.setUserId(uid);
        // ユーザ情報
        if (caseIdPetition3.size() > Constants.STR_CASE_STAGE_REPLY) {
            for (int i = 0; i < caseIdPetition3.size(); i++) {
                // ①取得したCaseId
                caseDetails3.setCaseId(caseIdPetition3.get(i).getCaseId());
                // ①取得したPetitionUserId
                caseDetails3.setPetitionUserId(caseIdPetition3.get(i).getPetitionUserId());
                // API「ケース詳細取得」を呼び出す
                ReturnResult returnResult3 = caseDetailCasesInfoSearch(caseDetails3);
                // 詳細内容の取得
                if (returnResult3.getCid() != null) {
                    // 最終戻り値設定
                    returnResultList.add(returnResult3);
                }
            }
        }
        // 要対応有無の降順 かつ 対応期日の昇順で結合後の２次元配列（もしくはリスト）をソートする
        if (returnResultList.size() != 0) {
            returnResultListSort = returnResultList.stream()
                    .sorted(Comparator.comparing(ReturnResult::getCorrespondence)
                            .reversed().thenComparing(ReturnResult::getCorrespondDate))
                    .collect(Collectors.toList());
        }
        return returnResultListSort;
    }
}
