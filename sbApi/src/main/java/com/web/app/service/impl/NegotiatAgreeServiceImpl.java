package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.Cases;
import com.web.app.domain.NegotiatAgree.CaseEstablish;
import com.web.app.mapper.UpdCaseEstablishMapper;
import com.web.app.service.NegotiatAgreeService;

/**
 * S20_和解案合意画面
 * Service層実現類
 * NegotiatAgreeServiceImpl
 * 
 * @author DUC 馮淑慧
 * @since 2024/05/09
 * @version 1.0
 */
@Service
public class NegotiatAgreeServiceImpl implements NegotiatAgreeService {

    @Autowired
    private UpdCaseEstablishMapper updCaseEstablishMapper;

    /**
     * API_案件成立更新
     * 和解案テーブルから取得した和解案Statusが6の場合、API_案件成立更新をコールし、案件のステータスを「成立」に更新する
     *
     * @param caseEstablish 更新に必要なセッション情報の和解案id、セッション情報の案件Caseとログインユーザ
     * @return num 案件成立更新成功件数
     */
    @Override
    public int updCaseEstablish(CaseEstablish caseEstablish) {

        // 「和解案確認更新API」をコール後、和解案確認データ（StatusとPayAmount）を取得する
        CaseNegotiations caseNegotiations = updCaseEstablishMapper
                .selectCaseNegotiations(caseEstablish.getCaseNegotiationsId());
        if (caseNegotiations.getStatus() == 6) {

            // 案件更新用数据初期化
            Cases cases = new Cases();

            // 金銭の支払い有無の設定
            // 取得した和解案のPayAmountが０より大きい場合、１で更新する。上記以外場合、０で更新する
            if (caseNegotiations.getPayAmount() > 0) {
                cases.setPayFlag(1);
            } else {
                cases.setPayFlag(0);
            }
            // ID
            cases.setCid(caseEstablish.getCasesId());
            // 上次修改者
            cases.setLastModifiedBy(caseEstablish.getLoginUser());

            // 案件成立更新
            return updCaseEstablishMapper.updateCases(cases);
        }

        return 0;
    }

}
