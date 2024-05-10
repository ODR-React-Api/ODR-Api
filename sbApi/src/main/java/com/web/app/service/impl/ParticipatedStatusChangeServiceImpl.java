package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.controller.ElevantPersonnelEmailAddressController;
import com.web.app.domain.ElevantPersonnelEmailAddressInfo;
import com.web.app.domain.ParticipatedStatusChangeResultInfo;
import com.web.app.domain.ParticipatedStatusChangeSelectInfo;
import com.web.app.mapper.ParticipatedStatusChangeMapper;
import com.web.app.service.ParticipatedStatusChangeService;

/**
 * ケース詳細取得設定
 *
 * @param param1 取得したの申立て番号、件名、登録日付、対応期日、状態、要対応有無
 * @param param2 API「 一覧取得」より渡された引数: 案件ID
 * @param param3 API「 一覧取得」より渡された引数: 立場フラグ
 * @param param4 API「 一覧取得」より渡された引数: ユーザーID
 * @return 戻り値はAPI「 一覧取得」に返される
 */
@Service
public class ParticipatedStatusChangeServiceImpl implements ParticipatedStatusChangeService {
    @Autowired
    private ElevantPersonnelEmailAddressController elevantPersonnelEmailAddressController;

    @Autowired
    private ParticipatedStatusChangeMapper ParticipatedStatusChangeMapper;

    @Override
    public ParticipatedStatusChangeResultInfo ParticipatedStatusChangeInfoSearch(String caseId) {
        // 1.参加表明対象ケースの状態の判定
        ParticipatedStatusChangeSelectInfo participationSel = ParticipatedStatusChangeMapper
                .participatedStatusChangeInfoSearch(caseId);
        System.out.println("caseから:" + caseId);
        System.out.println("cases participationSelから:" + participationSel);

        // 2.ケースの状態の更新
        ParticipatedStatusChangeResultInfo participatedFlag = participatedCaseStatusChangeUpdate(participationSel,
                caseId);

        return participatedFlag;

    }

    private ParticipatedStatusChangeResultInfo participatedCaseStatusChangeUpdate(
            ParticipatedStatusChangeSelectInfo participationSel, String caseId) {
        String cid = participationSel.getCId();
        Integer caseStage = participationSel.getCaseStage();
        String status = participationSel.getCaseStatus();

        ParticipatedStatusChangeResultInfo participatedStatusChangeResultInfo = new ParticipatedStatusChangeResultInfo();

        // 取得したCaseStageが0（回答） かつ CaseStatusが0000（申立後-参加待ち）の場合、以下の処理を実行
        if (caseStage == 0 && ("0000".equals(status))) {
            // ケース状態の更新
            int updateNum = ParticipatedStatusChangeMapper.caseStatusChangeUpdate(cid);
            System.out.println("update caseから:" + cid);
            System.out.println("update cases participationSelから:" + updateNum);

            if (updateNum != 0) {
                participatedStatusChangeResultInfo.setParticipatedFlag(0);
                System.out.println(
                        "111update participateFlag:" + participatedStatusChangeResultInfo.getParticipatedFlag());
            }

            // アクション履歴の登録 TODO

            // メール送信用関係者メアドの取得 TODO
            ElevantPersonnelEmailAddressInfo ElevantPersonnelEmailAddressInfo = elevantPersonnelEmailAddressController
                    .ElevantPersonnelEmailAddress(caseId);
            participatedStatusChangeResultInfo.setElevantPersonnelEmailAddressInfo(ElevantPersonnelEmailAddressInfo);

        } else {
            // 上記1.取得したCaseStageが0（回答）以外の場合、以下の処理を実行
            participatedStatusChangeResultInfo.setParticipatedFlag(1);
            System.out
                    .println("2222update participateFlag:" + participatedStatusChangeResultInfo.getParticipatedFlag());
        }

        return participatedStatusChangeResultInfo;

    }

}
