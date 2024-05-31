package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.AnswerLoginConfirm.UpdCases;

/**
 * S12 回答内容确认画面
 * API_案件更新
 * Mapper层
 * UpdCasesMapper
 * 
 * @author DUC 李文涛
 * @since 2024/05/30
 * @version 1.0
 */
@Mapper
public interface UpdCasesMapper {

    /**
      * 修改案件
      * @param UpdCases
      * @return 更新行数
      */
     int updCases(UpdCases updCases);
}
