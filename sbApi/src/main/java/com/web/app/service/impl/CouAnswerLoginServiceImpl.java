package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.web.app.domain.couAnswerLogin.RepliesContext;
import com.web.app.mapper.GetRepliesContextMapper;
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

    /**
     * 反訴・回答データ取得
     *
     * @param caseId セッション情報のcaseid
     * @param PlatformId セッション情報のプラットフォームID
     * @return getRepliesContextList
     * @throws Exception エラーの説明内容
     */ 
    @Override
    public List<RepliesContext> getRepliesContext(String CaseId,String PlatformId) {
        List<RepliesContext> getRepliesContextList = new ArrayList<RepliesContext>();
        getRepliesContextList = getRepliesContextMapper.getRepliesContext(CaseId,PlatformId);
        return getRepliesContextList;
    }
}