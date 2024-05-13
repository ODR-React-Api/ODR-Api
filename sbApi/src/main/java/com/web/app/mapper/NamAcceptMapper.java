package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.NamAccept.UpdMediatorHistories;

@Mapper
public interface NamAcceptMapper {

    int UpdMediatorHistories(UpdMediatorHistories updMediatorHistories);

}
