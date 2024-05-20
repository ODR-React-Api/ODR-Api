package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.couAnswerLogin.CasesByCid;
import java.util.List;

/**
 * 回答登録画面 Mapper
 * 
 * @author DUC 信召艶
 * @since 2024/04/29
 * @version 1.0
 */

@Mapper
public interface GetCasesByCidMapper {
    //API_反訴・回答データ取得
    List<CasesByCid> casesByCid(String CaseId,String PlatformId);
}
