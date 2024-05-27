package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.MedUserConfirm.MedUserConfirmSession;

@Mapper
public interface GetMediatorInfoMapper {
    // 解決件数取得
    int getSolveMediatorCount(MedUserConfirmSession medUserConfirmSession);

    // 調停件数取得
    int getMediatorCount(MedUserConfirmSession medUserConfirmSession);

}
