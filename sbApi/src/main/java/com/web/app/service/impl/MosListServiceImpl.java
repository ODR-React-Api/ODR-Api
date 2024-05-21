package com.web.app.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.MosList.CaseDetailCasesSelectInfo;
import com.web.app.domain.MosList.CaseIdListInfo;
import com.web.app.domain.MosList.ReturnResult;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.GetCaseDetailMapper;
import com.web.app.service.MosListService;

/**
 * 申立て一覧画面ServiceImpl
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/17
 * @version 1.0
 */
@Service
public class MosListServiceImpl implements MosListService {
    @Autowired
    private GetCaseDetailMapper getCaseDetailMapper;

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
        // 戻り値
        ReturnResult caseDetailCasesInfoItem = new ReturnResult();
        // 戻り値の申立て番号に「cases」から「ID」を取得したを設定された
        caseDetailCasesInfoItem.setCid(caseDetailCasesSelInfo.getCId());
        // 戻り値の状態に「cases」から「案件ステージ」を取得したを設定された
        caseDetailCasesInfoItem.setCaseStatus(caseDetailCasesSelInfo.getCaseStage().toString());
        // 戻り値の件名に「cases」から「タイトル名」を取得したを設定された
        caseDetailCasesInfoItem.setCaseTitle(caseDetailCasesSelInfo.getCaseTitle());
        // 戻り値の登録日付に「cases」から「申立て日」を取得したを設定された
        caseDetailCasesInfoItem.setPetitionDate(caseDetailCasesSelInfo.getPetitionDate());

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
                    if (mediationsInfoStatus == Constants.STR_CASE_MEDIATIONS_STATUS_1
                            || mediationsInfoStatus == Constants.STR_CASE_MEDIATIONS_STATUS_3
                            || mediationsInfoStatus == Constants.STR_CASE_MEDIATIONS_STATUS_8
                            || caseDetailCasesSelInfo.getGroupMessageFlag2() == Constants.STR_CASES_GROUPMESSAGEFLAG) {
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
                    if (mediationsInfoStatus == Constants.STR_CASE_MEDIATIONS_STATUS_1
                            || mediationsInfoStatus == Constants.STR_CASE_MEDIATIONS_STATUS_2
                            || mediationsInfoStatus == Constants.STR_CASE_MEDIATIONS_STATUS_7
                            || caseDetailCasesSelInfo.getGroupMessageFlag1() == Constants.STR_CASES_GROUPMESSAGEFLAG) {
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
}
