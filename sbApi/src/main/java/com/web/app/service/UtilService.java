package com.web.app.service;

import java.util.Date;
import java.util.List;

import com.web.app.domain.Entity.Cases;
import com.web.app.domain.Entity.MasterPlatforms;
import com.web.app.domain.Entity.MasterTypes;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.util.SendMailRequest;

/**
 * 工具類Service
 * 
 * @author DUC 耿浩哲
 * @since 2024/04/17
 * @version 1.0
 */
public interface UtilService {

    // Platform情報取得
    MasterPlatforms GetMasterPlatforms(String masterPlatforms);

    // ユーザ情報取得
    OdrUsers GetOdrUsersByUidOrEmail(String uid, String email, String platformId);

    // 案件情報取得
    Cases GetCasesByCid(String cid);

    // 種類マスタ表示名の置換
    String GetMasterDisplayName(String platformId, String languageId, String type, String typeValue);

    // メール送信
    boolean SendMail(SendMailRequest SendMailRequest);

    // Guid取得
    String GetGuid();

    // 指定した時間と日数
    Date AddDaysToDate(Date date, int days);

    // 種類マスタ情報取得
    List<MasterTypes> GetMasterTypeName(String type, String languageId, String platformId);

}
