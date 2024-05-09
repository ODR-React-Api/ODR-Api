package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.ScaleItems;
import com.web.app.domain.UserInfo;
import com.web.app.domain.InsRepliesTemp.CaseExtensionitemValues;
import com.web.app.domain.InsRepliesTemp.CaseFileRelations;
import com.web.app.domain.InsRepliesTemp.CasePetitions;
import com.web.app.domain.InsRepliesTemp.CaseRelations;
import com.web.app.domain.InsRepliesTemp.Files;


@Mapper
public interface InsRepliesTempMapper {
     //ユーザ情報取得
     UserInfo selectOdrUsers(String sessionId);
     //TBL「申立（case_petitions）」更新
     int casePetitionsUpd(CasePetitions casePetitions);
     //TBL「案件別個人情報リレーション（case_relations）」更新
     //自动采番的最大ID
     // String selectMaxId();
     int caseRelationsUpd(CaseRelations caseRelations);
     //案件-添付ファイルリレーション取得
     String caseFileRelationsSelect(String case_petitions_id);
     //TBL「添付ファイル（files）」を論理削除する
     int filesDelete(String fildId);
     //TBL「案件-添付ファイルリレーション（case_file_relations）」を論理削除する
     int caseFileRelationsDelete(String case_petitions_id);
     //⓹画面上添付ファイルがなくなるまで、TBL「添付ファイル（files）」を新規登録する。
     // a.TBL「添付ファイル（files）」を新規登録する
     //自动采番的最大ID
     // String selectFileMaxId();
     int insertFiles(Files files);
     // b.TBL「案件-添付ファイルリレーション（case_file_relations）」を新規登録する
     //自动采番的最大ID
     // String selectCaseFileRelationsMaxId();
     int insertCaseFileRelations(CaseFileRelations caseFileRelations);

     //既存の拡張項目内容を取得
     ScaleItems selectScaleItemIdValue(CaseExtensionitemValues CaseExtensionitemValues1);
     //拡張項目内容取得したデータがある場合、データを更新
     int updateCaseExtensionitemValues(CaseExtensionitemValues CaseExtensionitemValues2);
     //拡張項目内容取得したデータがないの場合、データを登録
     //自动采番的最大ID
     // String selectCaseExtensionitemValuesMaxId();
     int insertCaseExtensionitemValues(CaseExtensionitemValues CaseExtensionitemValues3);

}
