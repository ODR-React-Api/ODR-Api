package com.web.app.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.util.SendMailRequest;
import com.web.app.service.UtilService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "邮件模块")
@RestController
@RequestMapping("/sendmail")
public class SendMailController {

    @Autowired
    private UtilService utilService;

    @ApiOperation("邮件发送test")
    @PostMapping("/sendMail")
    public boolean sendMail() {

        SendMailRequest sendMailRequest = new SendMailRequest();

        sendMailRequest.setPlatformId("P025");

        sendMailRequest.setLanguageId("JP");

        sendMailRequest.setTempId("M026");

        sendMailRequest.setCaseId("0000000032");

        ArrayList<String> recipientEmail = new ArrayList<String>();

        recipientEmail.add("li.jian@trans-cosmos.com.cn");

        sendMailRequest.setRecipientEmail(recipientEmail);

        ArrayList<String> parameter = new ArrayList<String>();

        parameter.add("0000000032");
        parameter.add("メール受信テスト２_返金,その他");
        parameter.add("UserName");
        parameter.add("http://www.baidu.com");

        sendMailRequest.setParameter(parameter);

        sendMailRequest.setUserId("001");

        sendMailRequest.setControlType(1);

        boolean bool = utilService.SendMail(sendMailRequest);

        return true;

    }

}
