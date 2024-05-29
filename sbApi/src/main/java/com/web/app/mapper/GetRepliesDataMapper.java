package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.AnswerLogin.RepliesData;
import java.util.List;

/**
 * API_反訴・回答データ取得
 * 
 * @author DUC 信召艶
 * @since 2024/04/25
 * @version 1.0
 */
@Mapper
public interface GetRepliesDataMapper {
    //反訴・回答データ取得
    List<RepliesData> getReplies(String caseId, String platformId);
}

