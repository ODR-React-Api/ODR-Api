package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.NamAccept.UpdMediatorHistories;

/**
 * 調停人変更履歴変更Mapper
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/08
 * @version 1.0
 */
@Mapper
public interface UpdMediatorHistoriesMapper {

    // 調停人変更履歴変更
    int updMediatorHistories(UpdMediatorHistories updMediatorHistories);

}
