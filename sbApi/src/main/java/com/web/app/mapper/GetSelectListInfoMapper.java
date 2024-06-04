package com.web.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.MosList.TestMos;

/**
 * S3_申立て一覧画面
 * Mapper層
 * GetSelectListInfoMapper
 * API_検索用一覧取得
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/18
 * @version 1.0
 */
@Mapper
public interface GetSelectListInfoMapper {
    // 1.TBL「ユーザ情報」を取得する
    String testOdrUsersSearch(String sesionId);

    // 申立人の取得処理を行う。
    List<TestMos> testPetitionsSearch(String email);

    // 相手方の取得処理を行う。
    List<TestMos> testTraderFlgSearch(String email);

    // 調停人の取得処理を行う。
    List<TestMos> testMediatorSearch(String email);
}