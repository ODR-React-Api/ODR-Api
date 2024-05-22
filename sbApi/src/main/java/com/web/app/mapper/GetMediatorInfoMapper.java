package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.MedUserConfirm.MedUserConfirmSession;

@Mapper
public interface GetMediatorInfoMapper {

    int getMediatorCount(MedUserConfirmSession medUserConfirmSession);

    int getSolveMediatorCount(MedUserConfirmSession medUserConfirmSession);
}
