package com.web.app.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.MosList.CaseDetailCasesSelectInfo;
import com.web.app.domain.MosList.CaseIdListInfo;
import com.web.app.domain.MosList.ReturnResult;
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
     * @param param1 API「 一覧取得」より渡された引数
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
     * @param param1 取得したの申立て番号、件名、登録日付、対応期日、状態、要対応有無
     * @param param2 API「 一覧取得」より渡された引数: 案件ID
     * @param param3 API「 一覧取得」より渡された引数: 立場フラグ
     * @param param4 API「 一覧取得」より渡された引数: ユーザーID
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        // 対応期日初期値
        String dateString;
        // 「cases」から「案件ステージ」を取得したの判定：0:（申立）
        if (caseDetailCasesSelInfo.getCaseStage() == 0) {
            // 対応期日を文字列にフォーマットを設定する
            dateString = simpleDateFormat.format(caseDetailCasesSelInfo.getReplyEndDate());
        } else if (caseDetailCasesSelInfo.getCaseStage() == 2) {
            // 「cases」から「案件ステージ」を取得したの判定：2:（反訴）
            // 対応期日を文字列にフォーマットを設定する
            dateString = simpleDateFormat.format(caseDetailCasesSelInfo.getCounterclaimEndDate());
        } else if (caseDetailCasesSelInfo.getCaseStage() == 3) {
            // 「cases」から「案件ステージ」を取得したの判定：3:（交渉）
            // 対応期日を文字列にフォーマットを設定する
            dateString = simpleDateFormat.format(caseDetailCasesSelInfo.getNegotiationEndDate());
        } else if (caseDetailCasesSelInfo.getCaseStage() == 6 || caseDetailCasesSelInfo.getCaseStage() == 7) {
            // 「cases」から「案件ステージ」を取得したの判定：6:（調停人指名）7:（調停）
            // 対応期日を文字列にフォーマットを設定するする
            dateString = simpleDateFormat.format(caseDetailCasesSelInfo.getMediationEndDate());
        } else {
            dateString = "99999999";
        }
        // 対応期日を設定された
        caseDetailCasesInfoItem.setCorrespondDate(dateString);

        // 要対応有無の設定
        // 立場フラグが1（申立人）の場合
        if (idFlag == 1) {
            // 戻り値の立場フラグを設定された
            caseDetailCasesInfoItem.setPositionFlg(idFlag);

            // 「cases」から取得したの「案件ステージ」の比較
            switch (caseDetailCasesSelInfo.getCaseStage()) {
                case 2:
                    // 要対応有無に1（要対応）を設定する
                    caseDetailCasesInfoItem.setCorrespondence("1");
                    break;
                case 3:
                    // 「cases」から取得したの「交渉期限日変更ステータス」の比較
                    // 「case_negotiations」から取得したの「ステータス」の比較
                    if (caseDetailCasesSelInfo.getNegotiationEndDateChangeStatus() == 1 ||
                            (caseDetailCasesSelInfo.getCaseNegotiationsStatus() == null ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 1 ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 2 ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 3 ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 5 ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 7 ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 8 ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 12 ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 13 ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 14)) {
                        // 要対応有無に1（要対応）を設定する
                        caseDetailCasesInfoItem.setCorrespondence("1");
                    }
                    break;
                case 6:
                    // 要対応有無に1（要対応）を設定する
                    caseDetailCasesInfoItem.setCorrespondence("1");
                    break;
                case 7:
                    // 「case_mediations」から「ステータス」取得した
                    Integer mediationsInfoStatus = caseDetailCasesSelInfo.getMediationsStatus();
                    // 「case_mediations」から「ステータス」を取得したの比較
                    if (mediationsInfoStatus == 1 || mediationsInfoStatus == 3 || mediationsInfoStatus == 8
                            || caseDetailCasesSelInfo.getGroupMessageFlag2() == 1) {
                        // 要対応有無に1（要対応）を設定する
                        caseDetailCasesInfoItem.setCorrespondence("1");
                    }
                    break;
                default:
                    // 上記以外の場合、要対応有無に0（要対応なし）を設定する
                    caseDetailCasesInfoItem.setCorrespondence("0");
            }
        } else if (idFlag == 2) {
            // 立場フラグが2（相手方）の場合
            // 戻り値の立場フラグを設定された
            caseDetailCasesInfoItem.setPositionFlg(idFlag);

            // 「cases」から取得したの「案件ステージ」の比較
            switch (caseDetailCasesSelInfo.getCaseStage()) {
                case 0:
                    // 要対応有無に1（要対応）を設定する
                    caseDetailCasesInfoItem.setCorrespondence("1");
                    break;
                case 3:
                    // 「cases」から取得したの「交渉期限日変更ステータス」の比較
                    // 「case_negotiations」から取得したの「ステータス」の比較
                    if (caseDetailCasesSelInfo.getNegotiationEndDateChangeStatus() == 2 ||
                            (caseDetailCasesSelInfo.getCaseNegotiationsStatus() == null ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 0 ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 1 ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 3 ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 4 ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 9 ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 10 ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 11 ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 14 ||
                                    caseDetailCasesSelInfo.getCaseNegotiationsStatus() == 15)) {
                        // 要対応有無に1（要対応）を設定する
                        caseDetailCasesInfoItem.setCorrespondence("1");
                    }
                    break;
                case 6:
                    // 要対応有無に1（要対応）を設定する
                    caseDetailCasesInfoItem.setCorrespondence("1");
                    break;
                case 7:
                    // 「case_mediations」から「ステータス」を取得した
                    Integer mediationsInfoStatus = caseDetailCasesSelInfo.getMediationsStatus();
                    // 「case_mediations」から「ステータス」を取得したの比較
                    if (mediationsInfoStatus == 1 || mediationsInfoStatus == 2 || mediationsInfoStatus == 7
                            || caseDetailCasesSelInfo.getGroupMessageFlag1() == 1) {
                        // 要対応有無に1（要対応）を設定する
                        caseDetailCasesInfoItem.setCorrespondence("1");
                    }
                    break;
                default:
                    // 上記以外の場合、要対応有無に0（要対応なし）を設定する
                    caseDetailCasesInfoItem.setCorrespondence("0");
            }
        } else if (idFlag == 3) {
            // 立場フラグが3（調停人）の場合
            // 戻り値の立場フラグを設定された
            caseDetailCasesInfoItem.setPositionFlg(idFlag);

            // 要対応有無の設定
            // 「cases」から取得したの「案件ステージ」の比較
            switch (caseDetailCasesSelInfo.getCaseStage()) {
                case 6:
                    // 要対応有無に1（要対応）を設定する
                    caseDetailCasesInfoItem.setCorrespondence("1");
                    break;
                case 7:
                    // 「case_mediations」から取得した
                    Integer mediationsInfoStatus = caseDetailCasesSelInfo.getMediationsStatus();
                    if (mediationsInfoStatus == null || mediationsInfoStatus == 0) {
                        // 要対応有無に1（要対応）を設定する
                        caseDetailCasesInfoItem.setCorrespondence("1");
                    }
                    break;
                default:
                    // 上記以外の場合、要対応有無に0（要対応なし）を設定する
                    caseDetailCasesInfoItem.setCorrespondence("0");
            }

            // 未読メッセージ件数の取得
            if (caseDetailCasesSelInfo.getCaseStage() == 6) {
                // 1）未読メッセージ件数取得
                Integer messagesReadedCnt = 0;
                // 調停人情報開示フラグを取得
                Integer mediatorDisclosureFlagItem = getCaseDetailMapper
                        .caseDetailCasesMediatorDisclosureFlagInfoSearch(caseId);
                // 未読メッセージ取得（申立人・相手方・調停人受理開示後）
                if (mediatorDisclosureFlagItem == 1) {
                    // b ;未読メッセージ取得（申立人・相手方・調停人受理開示後）
                    messagesReadedCnt = getCaseDetailMapper.messagesReadedCntSearch(caseId, userId);

                    // 2）未読メッセージ有無の設定
                    if (messagesReadedCnt > 0) {
                        // 未読メッセージ件数の設定
                        caseDetailCasesInfoItem.setMsgCount(messagesReadedCnt);
                        // 3）要対応有無の設定（メッセージ向け）
                        // 要対応有無に1（要対応）を設定する
                        caseDetailCasesInfoItem.setCorrespondence("1");
                    } else {
                        // 未読メッセージ件数の設定
                        caseDetailCasesInfoItem.setMsgCount(0);
                    }
                } else {
                    // 未読メッセージ件数取得 （調停人＋受理後＋未開示）
                    // A.3.
                    Integer messagesUserMessagesReadedCnt = getCaseDetailMapper
                            .caseDetailMessagesUserMessagesReadedCntSearch(caseId, userId);

                    // 2）未読メッセージ有無の設定
                    if (messagesUserMessagesReadedCnt > 0) {
                        // 未読メッセージ件数の設定
                        caseDetailCasesInfoItem.setMsgCount(messagesUserMessagesReadedCnt);
                        // 3）要対応有無の設定（メッセージ向け）
                        // 要対応有無に1（要対応）を設定する
                        caseDetailCasesInfoItem.setCorrespondence("1");
                    } else {
                        // 未読メッセージ件数の設定
                        caseDetailCasesInfoItem.setMsgCount(0);
                    }
                }
            }

        }
        // 未読メッセージ件数の取得
        // b ;未読メッセージ取得（申立人・相手方・調停人受理開示後）
        Integer messagesReadedCnt = getCaseDetailMapper.messagesReadedCntSearch(caseId, userId);

        // 2）未読メッセージ有無の設定
        if (messagesReadedCnt > 0) {
            // 未読メッセージ件数の設定
            caseDetailCasesInfoItem.setMsgCount(messagesReadedCnt);
            // 3）要対応有無の設定（メッセージ向け）
            // 要対応有無に1（要対応）を設定する
            caseDetailCasesInfoItem.setCorrespondence("1");
        } else {
            // 未読メッセージ件数の設定
            caseDetailCasesInfoItem.setMsgCount(0);
        }
        return caseDetailCasesInfoItem;
    }
}
