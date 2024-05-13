package com.web.app.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.medUserConfirm.GetMediatorInfo;

/**
 * 調停人情報取得
 * 
 * @author DUC 賈文志
 * @since 2024/05/13
 * @version 1.0
 */
@Mapper
public interface GetMediatorInfoMapper {

    ArrayList<GetMediatorInfo> getMediatorInfo(String CaseId);
}
