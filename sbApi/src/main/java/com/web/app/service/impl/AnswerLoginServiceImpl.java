package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.answerLogin.GetReplies;
import com.web.app.mapper.GetRepliesDataMapper;
import com.web.app.service.AnswerLoginService;

/**
 * API_反訴・回答データ取得
 * 
 * @author DUC 信召艶
 * @since 2024/04/25
 * @version 1.0
 */
@Service
public class AnswerLoginServiceImpl  implements AnswerLoginService {
    @Autowired
    private GetRepliesDataMapper getRepliesDataMapper;
    
    /**
     * 反訴・回答データ取得
     *
     * @param caseId セッション情報のcaseid
     * @param PlatformId セッション情報のプラットフォームID
     * @return 取得した反訴・回答データ
     * @throws Exception エラー画面(404)へ遷移
     */    
    @Override
    public List<GetReplies> getReplies(String CaseId,String PlatformId) {
        List<GetReplies> list = new ArrayList<GetReplies>();
        list = getRepliesDataMapper.getReplies(CaseId,PlatformId);
        return list;
    }
}
