package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.CaseEstablish;
import com.web.app.domain.CaseNegotiations;
import com.web.app.domain.Entity.Cases;
import com.web.app.mapper.UpdCaseEstablishMapper;
import com.web.app.service.UpdCaseEstablishService;

@Service
public class UpdCaseEstablishServiceImpl implements UpdCaseEstablishService {


    @Autowired
    private UpdCaseEstablishMapper updCaseEstablishMapper;
    

    @Override
    public int UpdCases(CaseEstablish caseEstablish){

        //「和解案確認更新API」をコール後、和解案確認データ（StatusとPayAmount）を取得する
        CaseNegotiations caseNegotiations = updCaseEstablishMapper.selectCaseNegotiations(caseEstablish.getCaseNegotiationsId());
        if (caseNegotiations.getStatus() == 6) {
            Cases cases = new Cases();
            //金銭の支払い有無
            if (caseNegotiations.getPayAmount() > 0) {
                cases.setPayFlag(true);
            }else {
                cases.setPayFlag(false);
            }
            //ID
            cases.setCid(caseEstablish.getCasesId());
            //上次修改者
            cases.setLastModifiedBy(caseEstablish.getLoginUser());

            //案件成立更新
            return updCaseEstablishMapper.updateCases(cases);
        }

        return 0;
    }
    
}
