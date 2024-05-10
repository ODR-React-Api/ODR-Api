package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.NegotiatAgree.UpdNegotiatCon;

/**
 * 和解案確認更新API Mapper
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/02
 * @version 1.0
 */
@Mapper
public interface UpdNegotiatConMapper {

  // 和解案テーブルのStatusを取得
  Integer getNegotiationStatus(String caseId);

  // 和解案確認更新
  int setNegotiationStatus(UpdNegotiatCon updNegotiatCon);

  // ユーザメールをキーにTBL「案件別個人情報リレーション」より申立人と相手方なるケース取得
  UpdNegotiatCon getRelationsEmail(UpdNegotiatCon updNegotiatCon);

  // ユーザテーブルのEmailを取得
  UpdNegotiatCon getUsersEmail(UpdNegotiatCon updNegotiatCon);

}
