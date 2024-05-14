package com.web.app.service.impl;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.UserInfoConfirm.UserInfoModel;
import com.web.app.domain.constants.Constants;
import com.web.app.domain.constants.MailConstants;
import com.web.app.domain.constants.MessageConstants;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.mapper.RegisterUserMapper;
import com.web.app.service.UserInfoConfirmService;
import com.web.app.service.UtilService;

/**
 * ユーザ新規登録ServiceImpl
 * 
 * @author DUC 張万超
 * @since 2024/04/17
 * @version 1.0
 */

@Service
public class UserInfoConfirmServiceImpl implements UserInfoConfirmService {

    private static final Logger log = LogManager.getLogger(UserInfoConfirmServiceImpl.class);

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
        OdrUsers userInsert = new OdrUsers();
        userInsert.setUid(utilService.GetGuid());
        userInsert.setEmail(userInfo.getEmail());
        userInsert.setFirstName(userInfo.getFirstName());
        userInsert.setFirstName_kana(userInfo.getFirstNameKana());
        userInsert.setLastName(userInfo.getLastName());
        userInsert.setLastName_kana(userInfo.getLastNameKana());
        userInsert.setMiddleName(userInfo.getMiddleName());
        userInsert.setMiddleName_kana(userInfo.getMiddleNameKana());
        userInsert.setCompanyName(userInfo.getCompanyName());
        userInsert.setPassword(userInfo.getPassword());
        userInsert.setLastModifiedBy(userInfo.getLastModifiedBy());
        userInsert.setStatus(Constants.STR_ODR_USERS_INSERT_STATUS);
        userInsert.setMessageFrequency(Constants.STR_ODR_USERS_INSERT_MESSAGEFREQUENCY);
        userInsert.setTermsConfirmed(Constants.STR_ODR_USERS_INSERT_TERMSCONFIRMED);
        userInsert.setUserType(Constants.STR_ODR_USERS_INSERT_USERTYPE);
        userInsert.setDeleteFlag(Constants.STR_ODR_USERS_INSERT_DELETEFLAG);
        userInsert.setPlatformId(Constants.STR_ODR_USERS_INSERT_PLATFORMID);
        userInsert.setLanguageId(Constants.STR_ODR_USERS_INSERT_LANGUAGEID);

        // 新規ユーザーMapperの呼び出し
        int rerturnCount = registerUserMapper.registerUser(userInsert);

        // 増加本数が0でない場合
        if (rerturnCount != 0) {

            SendMailRequest sendMailRequest = new SendMailRequest();
            sendMailRequest.setPlatformId(Constants.STR_ODR_USERS_INSERT_PLATFORMID);
            sendMailRequest.setLanguageId(Constants.STR_ODR_USERS_INSERT_LANGUAGEID);
            sendMailRequest.setTempId(MailConstants.MailId_M002);

            ArrayList<String> recipientEmail = new ArrayList<String>();
            recipientEmail.add(userInsert.getEmail());
            sendMailRequest.setRecipientEmail(recipientEmail);

            ArrayList<String> parameter = new ArrayList<>();
            parameter.add(userInfo.getLastName() + Constants.SPACE_STRING + userInfo.getFirstName());
            parameter.add(Constants.REGISTERUSER_MAILID_M002_URL);
            sendMailRequest.setParameter(parameter);

            sendMailRequest.setUserId(Constants.REGISTERUSER_SENDMAIL_USERID);
            sendMailRequest.setControlType(Constants.REGISTERUSER_SENDMAIL_CONTROLTYPE);

            boolean bool = utilService.SendMail(sendMailRequest);

            // 送信が成功したかどうかを判断する
            if (!bool) {
                // 送信失敗印刷log
                log.error(MessageConstants.C00007E);
            }
        }

        return rerturnCount;
    }

}
