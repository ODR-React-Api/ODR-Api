package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.User;
import com.web.app.domain.Entity.ActionFileRelations;
import com.web.app.domain.Entity.ActionHistories;
import com.web.app.domain.Entity.Cases;
import com.web.app.domain.Entity.MailTemplates;
import com.web.app.domain.Entity.MasterPlatforms;
import com.web.app.domain.Entity.MasterTypes;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.util.DisplayNameResult;

@Mapper
public interface CommonMapper {

    // 根据platformID查询platform信息
    MasterPlatforms FindMasterPlatforms(String platformId);

    // 根据用户ID或用户邮箱查询用户信息
    OdrUsers FindUserByUidOrEmail(String uid, String email, String platformId);

    // 根据caseID查询案件信息
    Cases FindCasesInfoByCid(String cid);

    // 查询种类信息
    List<MasterTypes> FindMasterTypeName(String type, String languageId, String platformId);

    // 取得邮件模板
    List<MailTemplates> FindMailTemplatesList(String platformId, String tempId);

    User GetUserDataFromCaseIdentity(Boolean identity, String languageId, String platformId, String caseId);

    /**
     * 「アクション履歴」新規登録
     * 
     * @param actionHistories アクション履歴
     * @return 条の数を追加
     */
    int InsHistories(ActionHistories actionHistories);

    /**
     * 関係者内容取得
     * 
     * @param platformId プラットフォームのId
     * @param email      メールアドレス
     * @param uid        ユーザID
     * @return 関係者内容
     */
    DisplayNameResult FindDisplayName(String platformId, String email, String uid);

    /**
     * クエリーID
     * 
     * @param Id 新規のID
     * @return アクション履歴-添付ファイルリレーション
     */
    ActionFileRelations FindFileRelations(String Id);

    /**
     * 「アクション履歴-添付ファイルリレーション」新規登録
     * 
     * @param actionFileRelation アクション履歴-添付ファイルリレーション
     * @return 条の数を追加
     */
    int InsertActionFileRelations(ActionFileRelations actionFileRelation);
}
