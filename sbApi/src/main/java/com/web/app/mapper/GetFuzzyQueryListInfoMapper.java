package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MosList.SelectUserInfoForCase;

/**
 * 曖昧検索用一覧取得Mapper
 * 
 * @author DUC 張万超
 * @since 2024/04/29
 * @version 1.0
 */

@Mapper
public interface GetFuzzyQueryListInfoMapper {

    /**
     * ユーザ情報を取得する
     *
     * @param uid 画面.ユーザID
     * @return ユーザ情報
     */
    String getUserInfo(String uid);

    /**
     * ユーザメールをキーにTBL「案件別個人情報リレーション」より申立人となるケース取得
     *
     * @param email ユーザ情報
     * @return TBL「案件別個人情報リレーション」より申立人となるケース
     */
    List<SelectUserInfoForCase> selectUserInfoForCasesFromPetiton(String email);

    /**
     * ユーザメールをキーにTBL「案件別個人情報リレーション」より相手方となるケース取得
     *
     * @param email ユーザ情報
     * @return TBL「案件別個人情報リレーション」より相手方となるケース
     */
    List<SelectUserInfoForCase> selectUserInfoForCasesFromTrader(String email);

    /**
     * ユーザメールをキーにTBL「案件別個人情報リレーション」より調停人となるケース取得
     *
     * @param email ユーザ情報
     * @return TBL「案件別個人情報リレーション」より調停人となるケース
     */
    List<SelectUserInfoForCase> selectUserInfoForCasesFromMediator(String email);

}
