package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.IdPetitionUserId;
import com.web.app.domain.Entity.UpdateCaseRelations;
import com.web.app.domain.Entity.UserCase;


/**
 * 检索case_relations表
 */
@Mapper
public interface CaseRelationsMapper {

  // ユーザが申立人となるケースの取得
  List<UserCase> selectCaseIdPetition1(String email);

  // ユーザが相手方とするケースの取得
  List<UserCase> selectCaseIdPetition2(String email);

  // ユーザが調停人となるケースの取得 
  List<UserCase> selectCaseIdPetition3(String email);

  //TBL「案件別個人情報リレーション（case_relations）」と「申立（case_petitions）」より用意した下書き保存データを取得する
  IdPetitionUserId selectIdPetitionUserId(String uid);

  // TBL「案件別個人情報リレーション（case_relations）のMAXID情報を取得する
  String selectMaxId();

  // TBL「案件別個人情報リレーション（case_relations）」の更新
  int updateCaseRelations(UpdateCaseRelations updateCaseRelations, String casePetitions, String petitionUserId);

}
