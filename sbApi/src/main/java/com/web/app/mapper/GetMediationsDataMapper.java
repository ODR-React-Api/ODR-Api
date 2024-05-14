package com.web.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.mediationsMake.Mediation;

/**
 * API_ID:調停案データ取得API
 * 当案件の調停案下書きデータを検索し、画面項目へロードする
 * 
 * @author DUC 徐義然
 * @since 2024/05/07
 * @version 1.0
 */

@Mapper
public interface GetMediationsDataMapper {
    //調停案データ取得
    List<Mediation> selectMediationsData(String caseId,String platformId);
}
