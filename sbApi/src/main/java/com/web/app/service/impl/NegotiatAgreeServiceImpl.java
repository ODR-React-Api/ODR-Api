package com.web.app.service.impl;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.Entity.CaseNegotiations;
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
public class NegotiatAgreeServiceImpl implements NegotiatAgreeService {

    @Autowired
    private GetNegotiatConInfoMapper getNegotiatConInfoMapper;

    /**
     * 和解案確認データ取得
     *
     * @param NegotiatAgree セッション値
     * @return 置換後のテキスト
     */
    @Override
    public CaseNegotiations SelCaseNegotiations(String CaseID) {
        CaseNegotiations caseNegotiations = getNegotiatConInfoMapper.selCaseNegotiations(CaseID);
        String year = String.valueOf(LocalDate.now().getYear());
        String month = String.valueOf(LocalDate.now().getMonthValue());
        String day = String.valueOf(LocalDate.now().getDayOfMonth());
        if (caseNegotiations != null) {
            if (Num.NUM2.equals(caseNegotiations.getStatus()) ||
                    Num.NUM3.equals(caseNegotiations.getStatus()) ||
                    Num.NUM9.equals(caseNegotiations.getStatus()) ||
                    Num.NUM12.equals(caseNegotiations.getStatus()) ||
                    Num.NUM15.equals(caseNegotiations.getStatus())) {
                String htmlContext = caseNegotiations.getHtmlContext().replace("#YYYY#", year).replace("#MM#", month)
                        .replace("#DD#", day);
                String htmlContext2 = caseNegotiations.getHtmlContext2().replace("#YYYY#", year).replace("#MM#", month)
                        .replace("#DD#", day);
                caseNegotiations.setHtmlContext(htmlContext);
                caseNegotiations.setHtmlContext2(htmlContext2);
            } else {
                return null;
            }
        } else {
            return null;
        }
        return caseNegotiations;
    }

}
