package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MosList.DraftSavingDate;

/**
 * 申立て下書き保存データ取得Mapper
 * 
 * @author DUC 張万超
 * @since 2024/04/25
 * @version 1.0
 */

@Mapper
public interface GetSaveDataInfoMapper {

    /**
     * テーブルより下書き保存のデータを取得する。
     *
     * @param uid ユーザID
     * @return 関連ユーザの下書き保存のデータ
     */
    DraftSavingDate getSaveDataInfo(String uid);

}
