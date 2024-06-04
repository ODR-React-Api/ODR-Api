package com.web.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.MosLogin.FileId;
import com.web.app.domain.MosLogin.PetitionTemp;
import com.web.app.domain.MosLogin.ScaleItems;

/**
 * S8_申立登録画面
 * Mapper層
 * GetPetitionsTempMapper
 * API_申立て下書き保存データ取得
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/25
 * @version 1.0
 */
@Mapper
public interface GetPetitionsTempMapper {

     // TBL「案件別個人情報リレーション（case_relations）」とTBL「申立（case_petitions）」より関連ユーザの下書き保存のデータを取得する。
     PetitionTemp selectPetitionsTemp(String sessionId);

     // TBL「ユーザ（odr_users）」より申立人情報を取得する
     OdrUsers selectOdrUsers(String sessionId, String infoEmail);

     // TBL「案件-添付ファイルリレーション（case_file_relations）」より関連下書き案件のファイルIDを取得する。
     List<FileId> selectFileId(String casePetition);

     // 拡張項目内容取得
     List<ScaleItems> scaleItemsSearch(String platformId);
}
