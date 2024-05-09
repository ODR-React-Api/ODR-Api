package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.UserInsertModel;

/**
 * ユーザ新規登録Mapper
 * 
 * @author DUC 張万超
 * @since 2024/04/17
 * @version 1.0
 */

@Mapper
public interface RegisterUserMapper {

    /**
     * ユーザ新規登録
     *
     * @param userInsert 画面項目情報
     * @return 追加されたデータのエントリ数
     */
    public Integer registerUser(UserInsertModel userInsert);

}
