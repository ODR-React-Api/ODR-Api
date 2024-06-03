package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web.app.domain.OdrUserUtil;
import com.web.app.domain.constants.Constants;
import com.web.app.domain.constants.MailConstants;
import com.web.app.domain.constants.MessageConstants;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.mapper.RegisterUserMapper;
import com.web.app.service.UserInfoConfirmService;
import com.web.app.service.UtilService;

/**
 *
 * @author 李晓悦
 * @since 2024/05/20
 * @version 1.0
 * @description 用户service
 */
@Service
public class UserInfoConfirmServiceImpl implements UserInfoConfirmService {
    private static final Logger log = LogManager.getLogger(UserInfoConfirmServiceImpl.class);

    @Autowired
    private RegisterUserMapper registerUserMapper;

    @Autowired
    private UtilService utilService;

    /**
     * @description 用户注册
     * @param OdrUserUtil类包含OdrUser中的部分参数
     * @return 返回是否成功信息
     * @throws
     */
    @Override
    @Transactional
    @SuppressWarnings("rawtypes")
    public boolean registerUser(OdrUserUtil odrUserUtil) {
        // 设置用户信息默认值
        odrUserUtil.setUid(utilService.GetGuid());
        odrUserUtil.setPlatformId(Constants.PLANTFORM_ID);
        odrUserUtil.setLanguageId(Constants.JP);
        // 调用Mapper接口中的方法，将数据插入数据库
        int result = registerUserMapper.registerUserMapper(odrUserUtil);
        // 以下为邮件发送部分的内容
        SendMailRequest sendMailRequest = new SendMailRequest();
        sendMailRequest.setPlatformId(odrUserUtil.getPlatformId());
        sendMailRequest.setLanguageId(odrUserUtil.getLanguageId());
        List<String> emailList = new ArrayList<String>();
        emailList.add(odrUserUtil.getEmail());
        sendMailRequest.setRecipientEmail(emailList);
        List<String> parameter = new ArrayList<String>();
        parameter.add(odrUserUtil.getFirstName() + " " + odrUserUtil.getLastName());
        parameter.add(Constants.SEND_MESSAGE_URL);
        sendMailRequest.setParameter(parameter);
        sendMailRequest.setUserId(odrUserUtil.getUid());
        sendMailRequest.setControlType(Constants.CONTROL_TYPE);
        sendMailRequest.setTempId(MailConstants.MailId_M002);

        if (result != Constants.RESULT_STATE_SUCCESS) {
            return false;
        }
        // 调用邮件发送方法
        boolean bool = utilService.SendMail(sendMailRequest);
        // 若送信失败
        if (!bool) {
            log.error(MessageConstants.C00007E);
        }
        return true;
    }
}
