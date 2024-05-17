package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * ファイル名取得
 * 
 * @author DUC 李志文
 * @since 2024/05/16
 * @version 1.0
 */
@Mapper
public interface GetFileNameMapper {
    //添付ファイルテーブルからファイル名を取得
    String SelFile(String id);
}
