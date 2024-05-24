package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.AnswerLoginConfirm.UpdCases;
import com.web.app.domain.AnswerLoginConfirm.UpdCasesRelations;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.UpdCasesMapper;
import com.web.app.mapper.UpdCasesRelationsMapper;
import com.web.app.service.AnswerLoginConfirmService;

/**
 * S12 回答内容確認画面
 * Service層実現類
 * AnswerLoginConfirmServiceImpl
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */
@Service
public class AnswerLoginConfirmServiceImpl implements AnswerLoginConfirmService {

    @Autowired
    private UpdCasesRelationsMapper updCasesRelationsMapper;
    @Autowired
    private UpdCasesMapper updCasesMapper;

    /**
     * 代理人更新処理
     * API_案件別個人情報リレーションデータ更新
     * 下書きデータ存在する場合、条件によって、代理人Email（5個）がS11画面の入力項目に更新する。
     *
     * @return int DBへ更新した個数
     */
    @Override
    public int updateCaserelations(UpdCasesRelations caserelations) {

        // S11から 入力した代理人メールのList
        List<String> traderagentuserListAll = new ArrayList<>(caserelations.getTraderagentuserList());
        // S11から、五つのメールアドレスが必要 ⇒足りない場合：nullで補足
        while (traderagentuserListAll.size() < 5) {
            traderagentuserListAll.add(null);
        }
        // 補足後のList serviceに追加
        caserelations.setTraderagentuserListAll(traderagentuserListAll);
        return updCasesRelationsMapper.updateCaserelations(caserelations);
    }

    /**
     * 案件状態更新処理
     * API_案件更新
     * 条件によって、案件状態を更新する。
     * 反訴,反訴機能,交渉機能によって、更新する値が変わる
     *
     * @return int DBへ更新した個数
     */
    @Override
    public int updateCasecase(UpdCases casecase) {

        // S11から 入力した代理人メールのList
        List<String> traderagentuserListAll = new ArrayList<>(casecase.getTraderagentuserList());

        // 五つのメールアドレスが必要 ⇒足りない場合：nullで補足
        while (traderagentuserListAll.size() < Constants.TRADER_USER_EMAIL_LIST_lENGTH) {
            traderagentuserListAll.add(null);
        }

        // 反訴:あり ⇒値を設定
        if (casecase.getCounterclaimFlag() == Constants.COUNTER_CLAIM_FLAG_1) {
            casecase.setCaseStage(Constants.CASE_STAGE_2);
            casecase.setCaseStatus(Constants.CASE_STATUS_200);
            casecase.setCounterclaimFlag(Constants.COUNTER_CLAIM_FLAG_1);
            casecase.setCounterclaimStartDate(casecase.getNewDate());
            casecase.setCounterclaimEndDate(casecase.getOldDate());
            // 反訴(counterclaimFlag):なし 交渉機能(phaseNegotiation)：あり ⇒値を設定
        } else if (casecase.getCounterclaimFlag() == Constants.COUNTER_CLAIM_FLAG_0 && casecase.getPhaseNegotiation() == true) {
            casecase.setCaseStage(Constants.CASE_STAGE_3);
            casecase.setCaseStatus(Constants.CASE_STATUS_300);
            // 反訴(counterclaimFlag):なし 反訴機能(phaseReply)：あり/なし 交渉機能(phaseNegotiation)：あり
            // ⇒値を設定
        } else if (casecase.getCounterclaimFlag() == Constants.COUNTER_CLAIM_FLAG_0
                && (casecase.getPhaseReply() == true || casecase.getPhaseReply() == false)
                && casecase.getPhaseNegotiation() == true) {
            casecase.setNegotiationStartDate(casecase.getNewDate());
            casecase.setNegotiationEndDate(casecase.getOldDate());
            casecase.setNegotiationEndDateChangeStatus(Constants.NEGOTIATION_ENDDATE_CHANGE_STATUS_0);
            casecase.setNegotiationEndDateChangeCount(Constants.NEGOTIATION_ENDDATE_CHANGE_COUNT_0);
        }

        // 補足後のList serviceに追加
        casecase.setTraderagentuserListAll(traderagentuserListAll);
        return updCasesMapper.updateCasecase(casecase);
    }
}
