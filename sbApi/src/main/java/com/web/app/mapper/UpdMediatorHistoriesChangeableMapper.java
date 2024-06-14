package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MedUserChange.UpdMediatorHistoriesChangeable;

/**
 * 調停人変更履歴更新Mapper
 * 
 * @author DUC 耿浩哲
 * @since 2024/06/13
 * @version 1.0
 */
@Mapper
public interface UpdMediatorHistoriesChangeableMapper {

    // 調停人変更履歴更新API
    int updMediatorHistoriesChangeable(UpdMediatorHistoriesChangeable updMediatorHistoriesChangeable);

}
