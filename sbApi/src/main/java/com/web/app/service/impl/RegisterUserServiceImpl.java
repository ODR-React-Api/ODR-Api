package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.UserInfoModel;
import com.web.app.domain.UserInsertModel;
import com.web.app.domain.constants.MailConstants;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.mapper.RegisterUserMapper;
import com.web.app.service.RegisterUserService;
import com.web.app.service.UtilService;

/**
 * ユーザ新規登録ServiceImpl
 * 
 * @author DUC 張万超
 * @since 2024/04/17
 * @version 1.0
 */

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    private static final Logger log = LogManager.getLogger(RegisterUserServiceImpl.class);

    @Autowired
    private RegisterUserMapper registerUserMapper;

    @Autowired
    private UtilService utilService;

    /**
     * ユーザ新規登録
     *
     * @param userInfo 画面項目情報
     * @return 増加が成功したかどうか
     */

    @Override
    public Integer registerUser(UserInfoModel userInfo) {

        // 新規ユーザー情報の処理
        UserInsertModel userInsert = new UserInsertModel();
        userInsert.setUid(utilService.GetGuid());
        userInsert.setEmail(userInfo.getEmail());
        userInsert.setFirstName(userInfo.getFirstName());
        userInsert.setFirstNameKana(userInfo.getFirstNameKana());
        userInsert.setLastName(userInfo.getLastName());
        userInsert.setLastNameKana(userInfo.getLastNameKana());
        userInsert.setMiddleName(userInfo.getMiddleName());
        userInsert.setMiddleNameKana(userInfo.getMiddleNameKana());
        userInsert.setCompanyName(userInfo.getCompanyName());
        userInsert.setPassword(userInfo.getPassword());
        userInsert.setLastModifiedBy(userInfo.getLastModifiedBy());
        userInsert.setStatus(0);
        userInsert.setMessageFrequency(100);
        userInsert.setTermsConfirmed(1);
        userInsert.setUserType(0);
        userInsert.setDeleteFlag(0);
        userInsert.setPlatformId("0001");
        userInsert.setLanguageId("jp");

        // 新規ユーザーMapperの呼び出し
        int rertuenData = registerUserMapper.registerUser(userInsert);

        // 増加本数が0でない場合
        if (rertuenData != 0) {

            SendMailRequest sendMailRequest = new SendMailRequest();
            sendMailRequest.setPlatformId("0001");
            sendMailRequest.setLanguageId("JP");
            sendMailRequest.setTempId(MailConstants.MailId_M002);

            ArrayList<String> recipientEmail = new ArrayList<String>();
            recipientEmail.add(userInsert.getEmail());
            sendMailRequest.setRecipientEmail(recipientEmail);

            ArrayList<String> parameter = new ArrayList<>();
            parameter.add(userInfo.getLastName() + " " + userInfo.getFirstName());
            parameter.add("http://localhost:3000/");
            sendMailRequest.setParameter(parameter);

            sendMailRequest.setUserId("ODR_Front");
            sendMailRequest.setControlType(2);

            boolean bool = utilService.SendMail(sendMailRequest);

            // 送信が成功したかどうかを判断する
            if (!bool) {
                // 送信失敗印刷log
                log.error("通知メールの送信に失敗しました。");
            }
        }

        return rertuenData;
    }

}
