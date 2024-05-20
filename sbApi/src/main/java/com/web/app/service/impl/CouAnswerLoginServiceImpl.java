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
 * 反訴回答登録画面
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
     * @return getRepliesContextList
     * @throws Exception エラーの説明内容
     */ 
    @Override
    public List<GetRepliesContext> getRepliesContext(String CaseId,String PlatformId) {
        List<GetRepliesContext> getRepliesContextList = new ArrayList<GetRepliesContext>();
        getRepliesContextList = getRepliesContextMapper.getRepliesContext(CaseId,PlatformId);
        return getRepliesContextList;
    }

    /**
     * 案件データ取得
     *
     * @param caseId セッション情報のcaseid
     * @param PlatformId セッション情報のプラットフォームID
     * @return casesByCidList
     * @throws Exception エラーの説明内容
     */ 
    @Override
    public List<CasesByCid> casesByCid(String caseId,String platformId) {
        List<CasesByCid> casesByCidList = new ArrayList<CasesByCid>();
        casesByCidList = getCasesByCidMapper.casesByCid(caseId,platformId);
        return casesByCidList;
    }

}