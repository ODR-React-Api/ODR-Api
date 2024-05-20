package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.answerLogin.GetReplies;
import java.util.List;

/**
 * S11_回答登録画面
 * 
 * @author DUC 信召艶
 * @since 2024/04/25
 * @version 1.0
 */
@Mapper
public interface GetRepliesDataMapper {
    //反訴・回答データ取得
    List<GetReplies> getReplies(String caseId,String platformId);
}

