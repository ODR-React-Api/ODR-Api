package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import com.web.app.domain.couAnswerLogin.CasesByCid;
import com.web.app.domain.couAnswerLogin.GetRepliesContext;
import com.web.app.mapper.GetRepliesContextMapper;
import com.web.app.mapper.GetCasesByCidMapper;
import com.web.app.service.CouAnswerLoginService;

/**
 * API_反訴・回答データ取得
 * 
 * @author DUC 信召艶
 * @since 2024/04/29
 * @version 1.0
 */
@Service
public class CouAnswerLoginServiceImpl implements CouAnswerLoginService {
    
    //反訴・回答データ取得
    @Autowired
    private GetRepliesContextMapper getRepliesContextMapper;

    //案件データ取得
    @Autowired
    private GetCasesByCidMapper getCasesByCidMapper;
    
    /**
     * 反訴・回答データ取得
     *
     * @param caseId セッション情報のcaseid
     * @param PlatformId セッション情報のプラットフォームID
     * @return 取得した反訴・回答データ
     * @throws Exception エラー画面(404)へ遷移
     */ 
    @Override
    public List<GetRepliesContext> getRepliesContext(String CaseId,String PlatformId) {
        List<GetRepliesContext> list = new ArrayList<GetRepliesContext>();
        list = getRepliesContextMapper.getRepliesContext(CaseId,PlatformId);
        return list;
    }

    /**
     * 案件データ取得
     *
     * @param caseId セッション情報のcaseid
     * @param PlatformId セッション情報のプラットフォームID
     * @return 取得した案件データ
     * @throws Exception エラー画面(404)へ遷移
     */ 
    @Override
    public List<CasesByCid> casesByCid(String CaseId,String PlatformId) {
        List<CasesByCid> list = new ArrayList<CasesByCid>();
        list = getCasesByCidMapper.casesByCid(CaseId,PlatformId);
        return list;
    }

}