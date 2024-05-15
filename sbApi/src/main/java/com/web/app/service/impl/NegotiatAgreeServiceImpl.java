package com.web.app.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.NegotiatAgree.NegotiatAgree;
import com.web.app.domain.constants.Num;
import com.web.app.mapper.GetNegotiatConInfoMapper;
import com.web.app.service.NegotiatAgreeService;

/**
 * 和解案合意画面
 * 
 * @author DUC 李志文
 * @since 2024/05/14
 * @version 1.0
 */
@Service
public class NegotiatAgreeServiceImpl implements NegotiatAgreeService{

    @Autowired
    private GetNegotiatConInfoMapper getNegotiatConInfoMapper;

    /**
     * 和解案確認データ取得
     *
     * @param NegotiatAgree セッション値
     * @return 置換後のテキスト
     */
    @Override
    public CaseNegotiations selCaseNegotiations(NegotiatAgree negotiatAgree) {
        CaseNegotiations caseNegotiations = getNegotiatConInfoMapper.selCaseNegotiations(negotiatAgree);
        String year = String.valueOf(LocalDate.now().getYear());
        String month = String.valueOf(LocalDate.now().getMonthValue());
        String day = String.valueOf(LocalDate.now().getDayOfMonth());
        if (caseNegotiations.getStatus().equals(Num.NUM2) ||
            caseNegotiations.getStatus().equals(Num.NUM9) ||
            caseNegotiations.getStatus().equals(Num.NUM12) ||
            caseNegotiations.getStatus().equals(Num.NUM15)){
            String htmlContext = caseNegotiations.getHtmlContext().replace("#YYYY#", year).replace("#MM#", month).replace("#DD#", day);
            String htmlContext2 = caseNegotiations.getHtmlContext2().replace("#YYYY#", year).replace("#MM#", month).replace("#DD#", day);
            caseNegotiations.setHtmlContext(htmlContext);
            caseNegotiations.setHtmlContext(htmlContext2);
        }else if(caseNegotiations.getStatus().equals(Num.NUM3)){
            String htmlContext2 = caseNegotiations.getHtmlContext2().replace("#YYYY#", year).replace("#MM#", month).replace("#DD#", day);
            caseNegotiations.setHtmlContext(htmlContext2);
        }
        return caseNegotiations;
    }
    
}
