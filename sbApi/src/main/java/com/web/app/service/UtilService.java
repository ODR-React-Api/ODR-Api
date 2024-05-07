package com.web.app.service;

import com.web.app.domain.Entity.Cases;
import com.web.app.domain.Entity.MasterPlatforms;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.util.SendMailRequest;

public interface UtilService {

    // Platform取得
    MasterPlatforms GetMasterPlatforms(String masterPlatforms);

    // 用户信息取得
    OdrUsers GetOdrUsersByUidOrEmail(String uid, String email, String platformId);

    // 案件信息取得
    Cases GetCasesByCid(String cid);

    // 案件表示名生成
    String GetMasterDisplayName(String platformId, String languageId, String type, String typeValue);

    // mail送信
    boolean SendMail(SendMailRequest SendMailRequest);

    // Guid取得
    String GetGuid();
<<<<<<< HEAD
}
=======
}
>>>>>>> dev-xuxiaojiao
