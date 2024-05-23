package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.couAnswerLogin.RepliesContext;
import java.util.List;

/**
 * API_反訴・回答データ取得 Mapper
 * 
 * @author DUC 信召艶
 * @since 2024/04/29
 * @version 1.0
 */
@Mapper
public interface GetRepliesContextMapper {
    //反訴・回答データ取得
    List<RepliesContext> getRepliesContext(String caseId, String platformId);
}
