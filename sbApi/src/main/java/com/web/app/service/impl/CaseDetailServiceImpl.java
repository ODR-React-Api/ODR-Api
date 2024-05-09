package com.web.app.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.ReturnResult;
import com.web.app.domain.CaseDetailCasesSelectInfo;
import com.web.app.domain.CaseIdListInfo;
import com.web.app.mapper.CaseDetailMapper;
import com.web.app.service.CaseDetailService;

@Service
public class CaseDetailServiceImpl implements CaseDetailService {
    @Autowired
    private CaseDetailMapper caseDetailMapper;

    // ケース詳細調停案でcasesを取得
    @Override
    public ReturnResult CaseDetailCasesInfoSearch(CaseIdListInfo caseIdListInfo) {
        String caseId = caseIdListInfo.getCaseId();
        Integer idFlag = caseIdListInfo.getFlag();
        String userId = caseIdListInfo.getUserId();
        // 「cases」と「case_negotiations」から申立て番号、件名、登録日付、対応期日、状態、要対応有無の取得
        CaseDetailCasesSelectInfo caseDetailCasesInfo = caseDetailMapper
                .CaseDetailCasesInforSearch(caseId);
        System.out.println("111casesから:" + caseDetailCasesInfo);
        //
        ReturnResult caseInfo = CaseDetailMediationsInfoSearch(caseDetailCasesInfo, caseId, idFlag, userId);
        // System.out.println("111casesから:" + caseDetailCasesInfo);
        return caseInfo;
    }

    // ケース詳細調停案でcase_mediationsを取得
    private ReturnResult CaseDetailMediationsInfoSearch(CaseDetailCasesSelectInfo caseDetailCasesSelInfo,
            String caseId, Integer idFlag, String userId) {
        // CaseDetailCasesInfo caseDetailCasesInfo1 = new ArrayList();
        // 「cases」と「case_negotiations」から取得したの項目の順番取り

        // for (CaseDetailCasesInfo item : caseDetailCasesInfo) {
        // ケース詳細調停案 戻り値
        ReturnResult caseDetailCasesInfoItem = new ReturnResult();
        caseDetailCasesInfoItem.setCid(caseDetailCasesSelInfo.getCId());
        caseDetailCasesInfoItem.setCaseStatus(caseDetailCasesSelInfo.getCaseStage().toString());
        caseDetailCasesInfoItem.setCaseTitle(caseDetailCasesSelInfo.getCaseTitle());
        caseDetailCasesInfoItem.setPetitionDate(caseDetailCasesSelInfo.getPetitionDate());

        // 対応期日の設定
        // 0:（申立）2:（反訴）3:（交渉）6:（調停人指名）7:（調停）
        // SimpleDateFormatオブジェクトを作成する
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        if (caseDetailCasesSelInfo.getCaseStage() == 0) {
            // 対応期日を文字列にフォーマットを設定する
            String dateString = simpleDateFormat.format(caseDetailCasesSelInfo.getReplyEndDate());
            // 対応期日を設定された
            caseDetailCasesInfoItem.setCorrespondDate(dateString);
            // System.out.println("SolveDate dateNumberから:" + dateString);
        } else if (caseDetailCasesSelInfo.getCaseStage() == 2) {
            // 対応期日を文字列にフォーマットを設定する
            String dateString = simpleDateFormat.format(caseDetailCasesSelInfo.getCounterclaimEndDate());
            // 対応期日を設定された
            caseDetailCasesInfoItem.setCorrespondDate(dateString);
            // System.out.println("SolveDate dateNumberから:" + dateString);
        } else if (caseDetailCasesSelInfo.getCaseStage() == 3) {
            // 対応期日を文字列にフォーマットを設定する
            String dateString = simpleDateFormat.format(caseDetailCasesSelInfo.getNegotiationEndDate());
            // 対応期日を設定された
            caseDetailCasesInfoItem.setCorrespondDate(dateString);
            // System.out.println("SolveDate dateNumberから:" + dateString);

        } else if (caseDetailCasesSelInfo.getCaseStage() == 6 || caseDetailCasesSelInfo.getCaseStage() == 7) {
            // 対応期日を文字列にフォーマットを設定するする
            String dateString = simpleDateFormat.format(caseDetailCasesSelInfo.getMediationEndDate());
            // 対応期日を設定された
            caseDetailCasesInfoItem.setCorrespondDate(dateString);
            System.out.println("SolveDate dateNumberから:" + dateString);

        } else {
            String dateNumber = "99999999";
            // 対応期日を設定された
            caseDetailCasesInfoItem.setCorrespondDate(dateNumber);
            // System.out.println("SolveDate dateNumberから:" + dateNumber);
        }
        // System.out.println("111casesから:" + idFlag);

        // 要対応有無の設定
        // 立場フラグが1（申立人）の場合
        if (idFlag == 1) {
            caseDetailCasesInfoItem.setPositionFlg(idFlag);
            // System.out.println("idFlag:" + caseDetailCasesInfo);
            // System.out.println("111casesidFlagから:" + idFlag);

            // 「cases」と「case_negotiations」から取得したの状態の比較
            switch (caseDetailCasesSelInfo.getCaseStage()) {
                case 2:
                    caseDetailCasesInfoItem.setCorrespondence("1");
                    break;
                case 3:
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
                        caseDetailCasesInfoItem.setCorrespondence("1");
                    }
                    break;
                case 6:
                    caseDetailCasesInfoItem.setCorrespondence("1");
                    break;
                case 7:
                    // 「case_mediations」から取得した
                    // List<Integer> caseDetailMediationsInfo = caseDetailMapper
                    // .CaseDetailMediationsInfoSearch();
                    // System.out.println("222case_mediationsから:" + caseDetailMediationsInfo);
                    // 「case_mediations」から取得したの項目の順番取り
                    Integer MediationsInfoItem = caseDetailCasesSelInfo.getMediationsStatus();
                    // for (Integer MediationsInfoItem : caseDetailMediationsInfo) {
                    if (MediationsInfoItem == 1 || MediationsInfoItem == 3 || MediationsInfoItem == 8
                            || caseDetailCasesSelInfo.getGroupMessageFlag2() == 1) {
                        caseDetailCasesInfoItem.setCorrespondence("1");
                        // }
                    }
                    break;
                default:
                    caseDetailCasesInfoItem.setCorrespondence("0");
            }

            // 未読メッセージ件数の取得
            // b ;未読メッセージ取得（申立人・相手方・調停人受理開示後）
            Integer messagesReadedCntInfo = caseDetailMapper
                    .CaseDetailUserMessagesReadedCntInfoSearch(caseId, userId);
            // System.out.println("666UserMessagesから:" + messagesReadedCntInfo);

            // 2）未読メッセージ有無の設定
            if (messagesReadedCntInfo > 0) {
                // 未読メッセージ件数の設定
                caseDetailCasesInfoItem.setMsgCount(messagesReadedCntInfo);
                // 3）要対応有無の設定（メッセージ向け）
                caseDetailCasesInfoItem.setCorrespondence("1");
            } else {
                // 未読メッセージ件数の設定
                caseDetailCasesInfoItem.setMsgCount(0);
            }
        } else if (idFlag == 2) {
            // 立場フラグが2（相手方）の場合
            caseDetailCasesInfoItem.setPositionFlg(idFlag);
            // 「cases」と「case_negotiations」から取得したの状態の比較
            switch (caseDetailCasesSelInfo.getCaseStage()) {
                case 0:
                    caseDetailCasesInfoItem.setCorrespondence("1");
                    break;
                case 3:
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
                        caseDetailCasesInfoItem.setCorrespondence("1");
                    }
                    break;
                case 6:
                    caseDetailCasesInfoItem.setCorrespondence("1");
                    break;
                case 7:
                    // 「case_mediations」から取得した
                    // List<Integer> caseDetailMediationsInfo = caseDetailMapper
                    // .CaseDetailMediationsInfoSearch();
                    // System.out.println("333case_mediationsから:" + caseDetailMediationsInfo);

                    // 「case_mediations」から取得したの項目の順番取り
                    Integer MediationsInfoItem = caseDetailCasesSelInfo.getMediationsStatus();
                    // for (Integer MediationsInfoItem : caseDetailMediationsInfo) {
                    if (MediationsInfoItem == 1 || MediationsInfoItem == 2 || MediationsInfoItem == 7
                            || caseDetailCasesSelInfo.getGroupMessageFlag1() == 1) {
                        caseDetailCasesInfoItem.setCorrespondence("1");
                    }
                    // }
                    break;
                default:
                    caseDetailCasesInfoItem.setCorrespondence("0");
            }
            // 未読メッセージ件数の取得
            // b ;未読メッセージ取得（申立人・相手方・調停人受理開示後）
            Integer messagesReadedCntInfo = caseDetailMapper
                    .CaseDetailUserMessagesReadedCntInfoSearch(caseId, userId);
            // System.out.println("666UserMessagesから:" + messagesReadedCntInfo);

            // 2）未読メッセージ有無の設定
            if (messagesReadedCntInfo > 0) {
                // 未読メッセージ件数の設定
                caseDetailCasesInfoItem.setMsgCount(messagesReadedCntInfo);
                // 3）要対応有無の設定（メッセージ向け）
                caseDetailCasesInfoItem.setCorrespondence("1");
            } else {
                // 未読メッセージ件数の設定
                caseDetailCasesInfoItem.setMsgCount(0);
            }

        } else if (idFlag == 3) {
            // 立場フラグが3（調停人）の場合
            caseDetailCasesInfoItem.setPositionFlg(idFlag);

            // 「cases」と「case_negotiations」から取得したの状態の比較
            switch (caseDetailCasesSelInfo.getCaseStage()) {
                case 6:
                    // 要対応有無の設定値
                    caseDetailCasesInfoItem.setCorrespondence("1");
                    // 未読メッセージ件数の取得
                    // 1）未読メッセージ件数取得
                    // 調停人情報開示フラグを取得
                    Integer mediatorDisclosureFlagItem = caseDetailMapper
                            .CaseDetailCasesMediatorDisclosureFlagInfoSearch(
                                    caseId);
                    // System.out.println("555case_mediationsから:" + mediatorDisclosureFlagInfo);
                    // for (Integer mediatorDisclosureFlagItem : mediatorDisclosureFlagInfo) {
                    // 未読メッセージ取得（申立人・相手方・調停人受理開示後）
                    if (mediatorDisclosureFlagItem == 1) {
                        // System.out.println("666UserMessagesからuserId名前:" + userId);

                        // b ;未読メッセージ取得（申立人・相手方・調停人受理開示後）
                        Integer messagesReadedCntInfo = caseDetailMapper
                                .CaseDetailUserMessagesReadedCntInfoSearch(caseId, userId);

                        // System.out.println("666UserMessagesから:" + messagesReadedCntInfo);

                        // 2）未読メッセージ有無の設定
                        if (messagesReadedCntInfo > 0) {
                            // 未読メッセージ件数の設定
                            caseDetailCasesInfoItem.setMsgCount(messagesReadedCntInfo);
                            // 3）要対応有無の設定（メッセージ向け）
                            caseDetailCasesInfoItem.setCorrespondence("1");
                        } else {
                            // 未読メッセージ件数の設定
                            caseDetailCasesInfoItem.setMsgCount(0);
                        }
                    } else {
                        // 未読メッセージ件数取得 （調停人＋受理後＋未開示）
                        // A.3.
                        Integer messagesUserMessagesReadedCntInfo = caseDetailMapper
                                .CaseDetailMessagesUserMessagesReadedCntInfoSearch(caseId, userId);
                        System.out.println("777MessagesとUserMessagesから:" + messagesUserMessagesReadedCntInfo);

                        // 2）未読メッセージ有無の設定
                        if (messagesUserMessagesReadedCntInfo > 0) {
                            // 未読メッセージ件数の設定
                            caseDetailCasesInfoItem.setMsgCount(messagesUserMessagesReadedCntInfo);
                            // 3）要対応有無の設定（メッセージ向け）
                            caseDetailCasesInfoItem.setCorrespondence("1");
                        } else {
                            // 未読メッセージ件数の設定
                            caseDetailCasesInfoItem.setMsgCount(0);
                        }
                    }
                    // }
                    break;
                case 7:
                    // 「case_mediations」から取得した
                    // List<Integer> caseDetailMediationsInfo = caseDetailMapper
                    // .CaseDetailMediationsInfoSearch();
                    // System.out.println("444case_mediationsから:" + caseDetailMediationsInfo);
                    // 「case_mediations」から取得したの項目の順番取り
                    Integer MediationsInfoItem = caseDetailCasesSelInfo.getMediationsStatus();
                    // for (Integer MediationsInfoItem : caseDetailMediationsInfo) {
                    if (MediationsInfoItem == null || MediationsInfoItem == 0) {
                        caseDetailCasesInfoItem.setCorrespondence("1");
                    }
                    // }
                    break;
                default:
                    caseDetailCasesInfoItem.setCorrespondence("0");
            }

        }
        // }
        return caseDetailCasesInfoItem;
    }
}
