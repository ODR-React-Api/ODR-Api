package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.Entity.CaseExtensionitemValues;
import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CasePetitions;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.MosLogin.ScaleItems;

/**
 * S8_申立登録画面
 * Mapper層
 * InsRepliesTempMapper
 * API_申立て下書きデータ登録
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/30
 * @version 1.0
 */
@Mapper
public interface InsRepliesTempMapper {

     //ユーザ情報取得
     OdrUsers selectOdrUsers(String sessionId);

     //TBL「申立（case_petitions）」更新
     int casePetitionsUpd(CasePetitions casePetitions);

     //TBL「案件別個人情報リレーション（case_relations）」更新
     int caseRelationsUpd(CaseRelations caseRelations);

     //案件-添付ファイルリレーション取得
     String caseFileRelationsSelect(String case_petitions_id);

     //TBL「添付ファイル（files）」を論理削除する
     int filesDelete(String fildId);

     //TBL「案件-添付ファイルリレーション（case_file_relations）」を論理削除する
     int caseFileRelationsDelete(String case_petitions_id);
     
     //⓹画面上添付ファイルがなくなるまで、TBL「添付ファイル（files）」を新規登録する。
     // a.TBL「添付ファイル（files）」を新規登録する
     int insertFiles(Files files);

     // b.TBL「案件-添付ファイルリレーション（case_file_relations）」を新規登録する
     int insertCaseFileRelations(CaseFileRelations caseFileRelations);

     //既存の拡張項目内容を取得
     ScaleItems selectScaleItemIdValue(CaseExtensionitemValues CaseExtensionitemValues1);

     //拡張項目内容取得したデータがある場合、データを更新
     int updateCaseExtensionitemValues(CaseExtensionitemValues CaseExtensionitemValues2);
     
     //拡張項目内容取得したデータがないの場合、データを登録
     int insertCaseExtensionitemValues(CaseExtensionitemValues CaseExtensionitemValues3);

}
