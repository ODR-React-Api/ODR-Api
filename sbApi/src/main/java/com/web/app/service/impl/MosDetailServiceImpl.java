package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.controller.ElevantPersonnelEmailAddressController;
import com.web.app.domain.ElevantPersonnelEmailAddressInfo;
import com.web.app.domain.Entity.Cases;
import com.web.app.domain.MosDetail.ParticipatedStatusChangeResultInfo;
import com.web.app.mapper.UpdCasesStatusMapper;
import com.web.app.service.MosDetailService;

/**
 * 申立て詳細画面_概要
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
     * @param param1 参加表明する渡された引数: 案件ID
     * @return 戻り値は「 参照表明更新済FLG」に返される
     */
    @Override
    public ParticipatedStatusChangeResultInfo participatedStatusChangeInfoSearch(String caseId) {
        // 1.参加表明対象ケースの状態の取得判定
        Cases participationSel = updCasesStatusMapper.participatedStatusChangeInfoSearch(caseId);

        ParticipatedStatusChangeResultInfo participatedFlag = new ParticipatedStatusChangeResultInfo();
        // 2.ケースの状態の更新
        if (participationSel != null) {
            participatedFlag = participatedCaseStatusChangeUpdate(participationSel,
                    caseId);
            if (participatedFlag != null) {
                return participatedFlag;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 参加済状態変更
     * ケース状態の更新
     * 
     * @param param1 引数：参加表明対象ケースの状態に取得された
     * @return 戻り値は「 参照表明更新済FLG」に返される
     */
    private ParticipatedStatusChangeResultInfo participatedCaseStatusChangeUpdate(
            Cases participationSel, String caseId) {
        // テーブル「案件」から、「ID」を取得
        String cid = participationSel.getCid();
        // テーブル「案件」から、「案件ステージ」を取得
        Integer caseStage = participationSel.getCaseStage();
        // テーブル「案件」から、「案件ステータス」を取得
        String status = participationSel.getCaseStatus();

        ParticipatedStatusChangeResultInfo participatedStatusChangeResultInfo = new ParticipatedStatusChangeResultInfo();

        // テーブル「案件」から取得したCaseStageが0（回答） かつ CaseStatusが0000（申立後-参加待ち）の場合、以下の処理を実行
        if (caseStage == 0 && ("0000".equals(status))) {
            // ケース状態の更新
            int updateNum = updCasesStatusMapper.caseStatusChangeUpdate(cid);

            // ケース状態の更新の件数が0じゃない場合
            if (updateNum != 0) {
                participatedStatusChangeResultInfo.setParticipatedFlag(0);
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
            participatedStatusChangeResultInfo.setParticipatedFlag(1);
        }
        return participatedStatusChangeResultInfo;
    }
}
