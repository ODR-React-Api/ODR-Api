package com.web.app.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.medUserConfirm.GetMediatorGen;

/**
 * 調停人情報取得
 * 
 * @author DUC 賈文志
 * @since 2024/05/20
 * @version 1.0
 */
@Mapper
public interface GetMediatorGenMapper {
    // 調停人情報取得
    ArrayList<GetMediatorGen> getMediatorGen(String CaseId);
}
