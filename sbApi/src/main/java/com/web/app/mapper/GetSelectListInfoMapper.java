package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.getSelectListInfo.CaseRelations;
/**
     * API_検索用一覧取得
     * @author DUC 郝建润
     * @since 2024/06/04
     * @version 1.0
     */
@Mapper
public interface GetSelectListInfoMapper {
    //ユーザ情報を取得する
    String testEmail(String sesionId);
    //ユーザが申立人となるケースの取得
    List<CaseRelations> selectCasePetitionUserId1(String email);
    // ユーザが相手方となるケースの取得
    List<CaseRelations> selectCasePetitionUserId2(String email);
    // ユーザが調停人となるケースの取得
    List<CaseRelations> selectCasePetitionUserId3(String email);
}
