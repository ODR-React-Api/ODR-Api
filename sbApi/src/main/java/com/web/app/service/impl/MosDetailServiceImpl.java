package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.controller.ElevantPersonnelEmailAddressController;
import com.web.app.domain.ElevantPersonnelEmailAddressInfo;
import com.web.app.domain.Entity.Cases;
import com.web.app.domain.MosDetail.ParticipatedStatusChangeResultInfo;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.UpdCasesStatusMapper;
import com.web.app.service.MosDetailService;

/**
 * 申立て詳細画面_概要ServiceImpl
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Service
public class MosDetailServiceImpl implements MosDetailService {
    @Autowired
    private ElevantPersonnelEmailAddressController elevantPersonnelEmailAddressController;
    @Autowired
    private UpdCasesStatusMapper updCasesStatusMapper;

    /**
     * 参加済状態変更
     * 参加表明対象ケースの状態の取得
     * 
     * @param caseId 参加表明する渡された引数: 案件ID
     * @return 戻り値は「 参照表明更新済FLG」に返される
     */
    @Override
    public ParticipatedStatusChangeResultInfo participatedStatusSearch(String caseId) {
        // 1.参加表明対象ケースの状態の取得判定
        Cases participationSel = updCasesStatusMapper.participatedStatusSearch(caseId);
        ParticipatedStatusChangeResultInfo participatedFlag = new ParticipatedStatusChangeResultInfo();

        // 2.ケースの状態の更新
        if (participationSel != null) {
            participatedFlag = participatedCaseStatusChangeUpdate(participationSel, caseId);
            if (participatedFlag != null) {
                return participatedFlag;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * ケース状態の更新
     * 
     * @param participationSel 参加表明対象ケースの状態に取得された
     * @param caseId           案件ID
     * @return 戻り値は「 参照表明更新済FLG」に返される
     */
    private ParticipatedStatusChangeResultInfo participatedCaseStatusChangeUpdate(
            Cases participationSel, String caseId) {
        // 「ID」を取得
        String cid = participationSel.getCid();
        // 「案件ステージ」を取得
        Integer caseStage = participationSel.getCaseStage();
        // 「案件ステータス」を取得
        String status = participationSel.getCaseStatus();

        ParticipatedStatusChangeResultInfo participatedStatusChangeResultInfo = new ParticipatedStatusChangeResultInfo();

        // テーブル「cases」から取得したCaseStageが0（回答） かつ CaseStatusが0000（申立後-参加待ち）の場合、以下の処理を実行
        if (caseStage == Constants.STR_CASES_CASESTAGE_0 && (Constants.CASE_STATUS_0.equals(status))) {
            // ケース状態の更新
            int updateNum = updCasesStatusMapper.caseStatusChangeUpdate(cid);
            // ケース状態の更新の件数が0以外場合
            if (updateNum != Constants.UPDATE_NUMBER_0) {
                // 正常に更新の場合、参照表明更新済Flgに0（正常更新）を設定して、画面へ返す
                participatedStatusChangeResultInfo.setParticipatedFlag(Constants.PARTICIPATED_FLAG_0);
            }

            // アクション履歴の登録 TODO

            // メール送信用関係者メアドの取得 TODO
            ElevantPersonnelEmailAddressInfo ElevantPersonnelEmailAddressInfo = elevantPersonnelEmailAddressController
                    .ElevantPersonnelEmailAddress(caseId);
            if (ElevantPersonnelEmailAddressInfo != null) {
                participatedStatusChangeResultInfo
                        .setElevantPersonnelEmailAddressInfo(ElevantPersonnelEmailAddressInfo);
            }
        } else {
            // テーブル「案件」から取得したCaseStageが0（回答）以外の場合、以下の処理を実行
            // 正常に更新の場合、参照表明更新済Flgに1（更新不可）を設定し、画面へ返す
            participatedStatusChangeResultInfo.setParticipatedFlag(Constants.PARTICIPATED_FLAG_1);
        }
        return participatedStatusChangeResultInfo;
    }
}
